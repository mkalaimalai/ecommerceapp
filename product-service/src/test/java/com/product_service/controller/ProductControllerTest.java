package com.product_service.controller;

import com.mkalaimalai.common.dto.PageResponse;
import com.mkalaimalai.product_service.controller.ProductController;
import com.mkalaimalai.product_service.dto.ProductDTO;
import com.mkalaimalai.product_service.dto.ProductDTORequest;
import com.mkalaimalai.product_service.service.ProductService;

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

@WebFluxTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts_Success() {
        ProductDTO productResponse = ProductDTO.builder()
            .id(1L)
            .sku("TEST-001")
            .name("Test Product")
            .price(BigDecimal.valueOf(99.99))
            .stockQuantity(10)
            .build();

        PageResponse<ProductDTO> pageResponse = new PageResponse<>();
        pageResponse.setContent(List.of(productResponse));
        pageResponse.setPage(0);
        pageResponse.setSize(10);
        pageResponse.setTotalElements(1);
        pageResponse.setTotalPages(1);

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
            .jsonPath("$.content[0].sku").isEqualTo("TEST-001")
            .jsonPath("$.total_elements").isEqualTo(1);
    }

    // @Test
    void createProduct_Success() {
        ProductDTORequest request = new ProductDTORequest();
        request.setSku("TEST-001");
        request.setName("Test Product");
        request.setPrice(BigDecimal.valueOf(99.99));
        request.setStockQuantity(10);

        ProductDTO response = ProductDTO.builder()
            .id(1L)
            .sku("TEST-001")
            .name("Test Product")
            .price(BigDecimal.valueOf(99.99))
            .stockQuantity(10)
            .build();

        when(productService.createProduct(any(ProductDTORequest.class)))
            .thenReturn(Mono.just(response));

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
} 