package com.mkalaimalai.common.exception;

/**
 * Created by kalaimam on 9/6/17.
 */
public enum Error {

    ERR_USER_NOT_FOUND("User Not Found"),
    ERR_INTERNAL_SERVER_ERROR("Internal Server Error"),
    ERR_CONCURRENCY_FAILURE ("Concurrecy Failure"),
    ERR_ACCESS_DENIED ("Access Denied"),
    ERR_VALIDATION ("Validation Error"),
    ERR_METHOD_NOT_SUPPORTED ("Method Not Supported");

    protected String message;

    Error(String message) {
        this.message = message;
    }

    public String value() {
        return name();
    }

    public static Error fromValue(String v) {
        return valueOf(v);
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return name() + " message: " + message;
    }
}
