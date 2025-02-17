package com.example.customer.application.mapper;

import com.example.customer.application.dto.CustomerDTO;
import com.example.customer.application.dto.AddressDTO;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer dtoToDomain(CustomerDTO dto);
    CustomerDTO domainToDto(Customer domain);
    Address dtoToDomain(AddressDTO dto);
    AddressDTO domainToDto(Address domain);
} 