package com.mkalaimalai.customer_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.mkalaimalai.customer_service.domain.AddressType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private UUID id;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private boolean isDefault;
    private AddressType addressType;
} 