package com.product_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.mkalaimalai.product_service.dto.ProductDTO;
import com.mkalaimalai.product_service.dto.ProductDTORequest;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createProduct_Success() {
        ProductDTORequest request = new ProductDTORequest();
        request.setSku("TEST-001");
        request.setName("Test Product");
        request.setPrice(BigDecimal.valueOf(99.99));
        request.setStockQuantity(10);
        request.setCategory("Test Category");

        webTestClient.post()
            .uri("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(ProductDTO.class)
            .value(response -> {
                assert response.getSku().equals(request.getSku());
                assert response.getName().equals(request.getName());
                assert response.getPrice().compareTo(request.getPrice()) == 0;
            });
    }

    @Test
    void getProducts_Success() {
        webTestClient.get()
            .uri("/api/products")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.content").isArray()
            .jsonPath("$.total_elements").isNumber()
            .jsonPath("$.total_pages").isNumber();
    }
} 