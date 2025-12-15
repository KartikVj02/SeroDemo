package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPrefixException.class)
    public ResponseEntity<String> handlePrefix(InvalidPrefixException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

