package com.example.customer.domain.repository;

import com.example.customer.domain.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface R2dbcCustomerRepository extends R2dbcRepository<Customer, UUID> {
    Mono<Customer> findByEmail(String email);
} 