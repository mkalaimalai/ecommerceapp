package com.example.customer.application.dto;

import com.example.customer.domain.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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