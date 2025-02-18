package com.mkalaimalai.customer_service.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.mkalaimalai.customer_service.domain.Address;

import reactor.core.publisher.Flux;
import java.util.UUID;

public interface R2dbcAddressRepository extends R2dbcRepository<Address, UUID> {
    Flux<Address> findByCustomerId(UUID customerId);
} 