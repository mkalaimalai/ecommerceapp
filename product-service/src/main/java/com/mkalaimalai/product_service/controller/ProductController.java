package com.mkalaimalai.product_service.controller;

import com.mkalaimalai.common.dto.PageResponse;
import com.mkalaimalai.product_service.dto.ProductDTO;
import com.mkalaimalai.product_service.dto.ProductDTORequest;
import com.mkalaimalai.product_service.dto.ProductDTOUpdateRequest;
import com.mkalaimalai.product_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "API endpoints for product management")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products with pagination")
    public Mono<ResponseEntity<PageResponse<ProductDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        
        return productService.getAllProducts(PageRequest.of(page, size, Sort.by(sort)))
            .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
            .map(ResponseEntity::ok);
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Get product by SKU")
    public Mono<ResponseEntity<ProductDTO>> getProductBySku(@PathVariable String sku) {
        return productService.getProductBySku(sku)
            .map(ResponseEntity::ok);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category")
    public Flux<ProductDTO> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/brand/{brand}")
    @Operation(summary = "Get products by brand")
    public Flux<ProductDTO> getProductsByBrand(@PathVariable String brand) {
        return productService.getProductsByBrand(brand);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    public Mono<ResponseEntity<ProductDTO>> createProduct(@Valid @RequestBody ProductDTORequest request) {
        return productService.createProduct(request)
            .map(product -> ResponseEntity.status(HttpStatus.CREATED).body(product));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTOUpdateRequest request) {
        return productService.updateProduct(id, request)
            .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a product")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)
            .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
} 