package com.example.customer.domain;

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
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private boolean isDefault;
    private AddressType addressType;
    private UUID customerId;
}

