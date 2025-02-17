package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.ecommerce.dto.CreateProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductResponse productResponse;
    private CreateProductRequest createRequest;

    @BeforeEach
    void setUp() {
        product = Product.builder()
            .id(1L)
            .sku("TEST-001")
            .name("Test Product")
            .price(BigDecimal.valueOf(99.99))
            .stockQuantity(10)
            .build();

        productResponse = ProductResponse.builder()
            .id(1L)
            .sku("TEST-001")
            .name("Test Product")
            .price(BigDecimal.valueOf(99.99))
            .stockQuantity(10)
            .build();

        createRequest = new CreateProductRequest();
        createRequest.setSku("TEST-001");
        createRequest.setName("Test Product");
        createRequest.setPrice(BigDecimal.valueOf(99.99));
        createRequest.setStockQuantity(10);
    }

    @Test
    void getAllProducts_Success() {
        when(productRepository.count()).thenReturn(Mono.just(1L));
        when(productRepository.findAllBy(any(PageRequest.class))).thenReturn(Flux.just(product));
        when(productMapper.toResponse(product)).thenReturn(productResponse);

        StepVerifier.create(productService.getAllProducts(PageRequest.of(0, 10)))
            .expectNextMatches(page -> 
                page.getContent().size() == 1 &&
                page.getTotalElements() == 1 &&
                page.getPage() == 0)
            .verifyComplete();
    }

    @Test
    void createProduct_Success() {
        when(productRepository.existsBySku(createRequest.getSku())).thenReturn(Mono.just(false));
        when(productMapper.toEntity(createRequest)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));
        when(productMapper.toResponse(product)).thenReturn(productResponse);

        StepVerifier.create(productService.createProduct(createRequest))
            .expectNext(productResponse)
            .verifyComplete();
    }
} 