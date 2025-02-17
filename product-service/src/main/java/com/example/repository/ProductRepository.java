package com.example.repository;

import com.example.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
    Flux<Product> findAllBy(Pageable pageable);
    Mono<Long> count();
    Flux<Product> findByCategory(String category);
    Flux<Product> findByBrand(String brand);
    Mono<Product> findBySku(String sku);
    Mono<Boolean> existsBySku(String sku);
} 