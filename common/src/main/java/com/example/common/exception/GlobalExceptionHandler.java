package com.example.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.List;


/**
 * Created by kalaimam on 9/5/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse resourceNotFound(ResourceNotFoundException ex) {
        logger.error("Global exception {}", ex);
        return new ErrorResponse(Error.ERR_USER_NOT_FOUND.name(),Error.ERR_USER_NOT_FOUND.message());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(MethodArgumentNotValidException ex) {
        logger.error("Global exception {}", ex);
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors =  result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse processAccessDeniedException(AccessDeniedException ex) {
        logger.error("Global exception {}", ex);
        return new ErrorResponse(Error.ERR_ACCESS_DENIED.name(), ex.getMessage());
    }

    private ErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        ErrorResponse dto = new ErrorResponse(Error.ERR_VALIDATION.name(), Error.ERR_VALIDATION.message());

        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }

        return dto;
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    public ErrorResponse processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
//        logger.error("Global exception {}", exception);
//        return new ErrorResponse(Error.ERR_METHOD_NOT_SUPPORTED.name(), exception.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> processRuntimeException(Exception ex) {
        ResponseEntity.BodyBuilder builder;
        ErrorResponse errorVM;
        logger.error("Global exception {}", ex);
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorVM = new ErrorResponse("error." + responseStatus.value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorVM = new ErrorResponse(Error.ERR_INTERNAL_SERVER_ERROR.name(), ex.toString());
        }
        return builder.body(errorVM);
    }

}
