package com.neurogene.store.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInputValidationException(MethodArgumentNotValidException ex) {
        LOG.error("Invalid request parameters: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Error creating/updating store due to bad request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        LOG.error("System Error occurred, please contact the administrator {}", ex.getMessage(), ex);
        return new ResponseEntity<>("System Error occurred, please contact the administrator", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
