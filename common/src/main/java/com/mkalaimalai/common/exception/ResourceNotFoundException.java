package com.mkalaimalai.common.exception;

/**
 * Created by kalaimam on 9/5/17.
 */
public class ResourceNotFoundException extends RuntimeException {

    private Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
    public ResourceNotFoundException( String message) {
        super(message);
    }
}
