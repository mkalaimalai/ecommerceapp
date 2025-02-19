package com.mkalaimalai.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    
    @JsonProperty("stock_quantity")
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
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
} 