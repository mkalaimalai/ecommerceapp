package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    
    @JsonProperty("stock_quantity")
    @Positive(message = "Stock quantity must be positive")
    private Integer stockQuantity;
    
    private String brand;
    private String category;
    
    @JsonProperty("sub_category")
    private String subCategory;
    
    @JsonProperty("image_url")
    private String imageUrl;
    
    private Double weight;
    private String dimensions;
    private String color;
    private String material;
    
    @JsonProperty("is_active")
    private Boolean isActive;
} 