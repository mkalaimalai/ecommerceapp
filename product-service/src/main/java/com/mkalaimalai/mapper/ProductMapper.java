package com.mkalaimalai.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mkalaimalai.dto.ProductDTO;
import com.mkalaimalai.dto.ProductDTORequest;
import com.mkalaimalai.dto.ProductDTOUpdateRequest;
import com.mkalaimalai.model.Product;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
    ProductDTO toResponse(Product product);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Product toEntity(ProductDTORequest request);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Product product, ProductDTOUpdateRequest request);
} 