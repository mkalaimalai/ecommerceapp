package com.example.customer.application.service;

import com.example.customer.application.dto.CustomerDTO;
import com.example.customer.application.mapper.CustomerMapper;
import com.example.customer.domain.Customer;
import com.example.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    public Flux<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .map(customerMapper::domainToDto);
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