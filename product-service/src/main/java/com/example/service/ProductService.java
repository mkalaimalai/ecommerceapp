package com.example.service;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductResponse;
import com.example.dto.UpdateProductRequest;
import com.example.common.dto.PageResponse;
import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Mono<PageResponse<ProductResponse>> getAllProducts(Pageable pageable) {
        return productRepository.count()
            .flatMap(total -> productRepository.findAllBy(pageable)
                .map(productMapper::toResponse)
                .collectList()
                .map(products -> PageResponse.<ProductResponse>builder()
                    .content(products)
                    .page(pageable.getPageNumber())
                    .size(pageable.getPageSize())
                    .totalElements(total)
                    .totalPages((int) Math.ceil((double) total / pageable.getPageSize()))
                    .build()
                )
            );
    }

    public Mono<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id)
            .map(productMapper::toResponse)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    public Mono<ProductResponse> getProductBySku(String sku) {
        return productRepository.findBySku(sku)
            .map(productMapper::toResponse)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    public Flux<ProductResponse> getProductsByCategory(String category) {
        return productRepository.findByCategory(category)
            .map(productMapper::toResponse);
    }

    public Flux<ProductResponse> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand)
            .map(productMapper::toResponse);
    }

    public Mono<ProductResponse> createProduct(CreateProductRequest request) {
        return productRepository.existsBySku(request.getSku())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "SKU already exists"));
                }
                Product product = productMapper.toEntity(request);
                product.setCreatedAt(LocalDateTime.now());
                product.setUpdatedAt(LocalDateTime.now());
                return productRepository.save(product);
            })
            .map(productMapper::toResponse);
    }

    public Mono<ProductResponse> updateProduct(Long id, UpdateProductRequest request) {
        return productRepository.findById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")))
            .flatMap(product -> {
                productMapper.updateEntity(product, request);
                product.setUpdatedAt(LocalDateTime.now());
                return productRepository.save(product);
            })
            .map(productMapper::toResponse);
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.findById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")))
            .flatMap(product -> productRepository.deleteById(id));
    }
} 