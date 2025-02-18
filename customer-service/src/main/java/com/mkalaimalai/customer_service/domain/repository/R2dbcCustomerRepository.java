package com.mkalaimalai.customer_service.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.mkalaimalai.customer_service.domain.Customer;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface R2dbcCustomerRepository extends R2dbcRepository<Customer, UUID> {
    Mono<Customer> findByEmail(String email);
} 