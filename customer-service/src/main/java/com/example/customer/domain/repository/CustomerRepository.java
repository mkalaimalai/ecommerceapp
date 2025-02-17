package com.example.customer.domain.repository;

import com.example.customer.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CustomerRepository {
    Mono<Customer> save(Customer customer);
    Mono<Customer> findById(UUID id);
    Flux<Customer> findAll();
    Mono<Void> deleteById(UUID id);
    Mono<Customer> findByEmail(String email);
} 