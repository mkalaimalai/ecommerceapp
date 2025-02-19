package com.mkalaimalai.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTORequest {
    @NotBlank(message = "SKU is required")
    private String sku;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    
    @JsonProperty("stock_quantity")
    @NotNull(message = "Stock quantity is required")
    @Positive(message = "Stock quantity must be positive")
    private Integer stockQuantity;
    
    private String brand;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @JsonProperty("sub_category")
    private String subCategory;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    private Double weight;
    private String dimensions;
    private String color;
    private String material;
} 