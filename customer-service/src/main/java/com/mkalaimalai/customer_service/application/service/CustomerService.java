package com.mkalaimalai.customer_service.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mkalaimalai.customer_service.application.dto.CustomerDTO;
import com.mkalaimalai.customer_service.application.mapper.CustomerMapper;
import com.mkalaimalai.customer_service.domain.repository.CustomerRepository;


import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;
import com.mkalaimalai.common.dto.PageResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        return Mono.just(customerDTO)
                .map(customerMapper::dtoToDomain)
                .flatMap(customerRepository::save)
                .map(customerMapper::domainToDto);
    }

    public Mono<CustomerDTO> getCustomer(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::domainToDto)
                .switchIfEmpty(Mono.error(new RuntimeException("Customer not found")));
    }

    public Mono<PageResponse<CustomerDTO>> getAllCustomers(Pageable pageable) {
        return customerRepository.count()
            .flatMap(total -> customerRepository.findAllBy(pageable)
                .map(customerMapper::domainToDto)
                .collectList()
                .map(customers -> PageResponse.<CustomerDTO>builder()
                    .content(customers)
                    .page(pageable.getPageNumber())
                    .size(pageable.getPageSize())
                    .totalElements(total)
                    .totalPages((int) Math.ceil((double) total / pageable.getPageSize()))
                    .build()
                )
            );
    }

    public Mono<CustomerDTO> updateCustomer(UUID id, CustomerDTO customerDTO) {
        return getCustomer(id)
                .map(existing -> {
                    customerDTO.setId(existing.getId());
                    return customerDTO;
                })
                .map(customerMapper::dtoToDomain)
                .flatMap(customerRepository::save)
                .map(customerMapper::domainToDto);
    }

    public Mono<Void> deleteCustomer(UUID id) {
        return customerRepository.deleteById(id);
    }
} 