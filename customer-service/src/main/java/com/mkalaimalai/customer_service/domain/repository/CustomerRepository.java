package com.mkalaimalai.customer_service.domain.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;
import org.springframework.data.domain.Pageable;


import com.mkalaimalai.customer_service.domain.Customer;

public interface CustomerRepository {
    Mono<Customer> save(Customer customer);
    Mono<Customer> findById(UUID id);
    Flux<Customer> findAllBy(Pageable pageable);
    Mono<Long> count();
    Mono<Void> deleteById(UUID id);
    Mono<Customer> findByEmail(String email);
} 