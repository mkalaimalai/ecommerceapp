package com.example.controller;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductResponse;
import com.example.common.dto.PageResponse;
import com.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts_Success() {
        PageResponse<ProductResponse> pageResponse = PageResponse.<ProductResponse>builder()
            .content(List.of(createSampleProductResponse()))
            .page(0)
            .size(10)
            .totalElements(1)
            .totalPages(1)
            .build();

        when(productService.getAllProducts(any(PageRequest.class)))
            .thenReturn(Mono.just(pageResponse));

        webTestClient.get()
            .uri("/api/products")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.content").isArray()
            .jsonPath("$.content[0].id").isEqualTo(1)
            .jsonPath("$.total_elements").isEqualTo(1);
    }

    @Test
    void createProduct_Success() {
        CreateProductRequest request = new CreateProductRequest();
        request.setSku("TEST-001");
        request.setName("Test Product");
        request.setPrice(BigDecimal.valueOf(99.99));
        request.setStockQuantity(10);

        when(productService.createProduct(any(CreateProductRequest.class)))
            .thenReturn(Mono.just(createSampleProductResponse()));

        webTestClient.post()
            .uri("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.id").isEqualTo(1)
            .jsonPath("$.sku").isEqualTo("TEST-001");
    }

    private ProductResponse createSampleProductResponse() {
        return ProductResponse.builder()
            .id(1L)
            .sku("TEST-001")
            .name("Test Product")
            .price(BigDecimal.valueOf(99.99))
            .stockQuantity(10)
            .build();
    }
} 