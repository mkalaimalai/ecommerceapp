package com.example.common.exception;


/**
 * Created by kalaimam on 9/8/17.
 */
public class FieldError {

    private static final long serialVersionUID = 1L;

    private final String objectName;

    private final String field;

    private final String message;

    public FieldError(String dto, String field, String message) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
