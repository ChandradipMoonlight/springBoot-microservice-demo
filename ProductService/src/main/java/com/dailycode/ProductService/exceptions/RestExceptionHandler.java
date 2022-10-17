package com.dailycode.ProductService.exceptions;

import com.dailycode.ProductService.model.ErrorResponse;
import com.dailycode.ProductService.model.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProductCustomException.class)
    public ResponseEntity<ErrorResponse> productCustomExceptionHandler(ProductCustomException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getType().name(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
