package com.example.customer.domain.repository;

import com.example.customer.domain.Address;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface R2dbcAddressRepository extends R2dbcRepository<Address, UUID> {
    Flux<Address> findByCustomerId(UUID customerId);
} 