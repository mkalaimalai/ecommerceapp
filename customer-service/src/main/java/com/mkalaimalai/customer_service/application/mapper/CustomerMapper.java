package com.mkalaimalai.customer_service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mkalaimalai.customer_service.application.dto.AddressDTO;
import com.mkalaimalai.customer_service.application.dto.CustomerDTO;
import com.mkalaimalai.customer_service.domain.Address;
import com.mkalaimalai.customer_service.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer dtoToDomain(CustomerDTO dto);
    CustomerDTO domainToDto(Customer domain);
    Address dtoToDomain(AddressDTO dto);
    AddressDTO domainToDto(Address domain);
} 