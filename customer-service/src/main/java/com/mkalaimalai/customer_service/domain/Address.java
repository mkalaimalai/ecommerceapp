package com.mkalaimalai.customer_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("addresses")
public class Address {
    @Id
    private UUID id;
    private UUID customerId;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Boolean isDefault;
    private String addressType; // SHIPPING, BILLING, etc.
}