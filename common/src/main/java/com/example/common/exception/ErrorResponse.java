package com.example.common.exception;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalaimam on 9/5/17.
 */
@Data
public class ErrorResponse {

    private static final long serialVersionUID = 1L;

    private String message;
    private String description;
    private List<FieldError> fieldErrors;

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public ErrorResponse(String message, String description, List<FieldError> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldError(objectName, field, message));
    }


}
