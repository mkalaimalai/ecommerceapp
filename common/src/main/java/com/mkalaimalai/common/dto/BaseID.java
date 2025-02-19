package com.mkalaimalai.common.dto;

import lombok.Data;

@Data
public abstract class BaseID<T> {

    private final T value;

}
