package com.mkalaimalai.customer_service.application.dto;

import com.mkalaimalai.common.dto.BaseID;

import java.util.UUID;

public class CustomerID extends BaseID<UUID> {

    public CustomerID(UUID value) {
        super(value);
    }
}
