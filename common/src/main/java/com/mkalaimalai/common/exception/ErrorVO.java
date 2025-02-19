package com.mkalaimalai.common.exception;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalaimam on 9/5/17.
 */
@Data
public class ErrorVO {

    private static final long serialVersionUID = 1L;

    private String message;
    private String description;
    private List<FieldErrorVO> fieldErrors;

    public ErrorVO(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public ErrorVO(String message, String description, List<FieldErrorVO> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorVO(objectName, field, message));
    }


}
