package com.goorm.devlink.matchingservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "com.goorm.devlink.matchingservice.controller")
public class ControllerExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResult> noSuchElementExceptionHandler(NoSuchElementException exception,
                                                                     HttpServletRequest request){
        return ResponseEntity.badRequest()
                .body(ErrorResult.getInstance(exception.getMessage(), request.getRequestURL().toString()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> noSuchElementExceptionHandler(IllegalArgumentException exception,
                                                                     HttpServletRequest request){
        return ResponseEntity.badRequest()
                .body(ErrorResult.getInstance(exception.getMessage(), request.getRequestURL().toString()));
    }
}
