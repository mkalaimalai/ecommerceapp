package com.mkalaimalai.customer_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("customers")
public class Customer {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    @Transient
    private List<Address> addresses;
} 