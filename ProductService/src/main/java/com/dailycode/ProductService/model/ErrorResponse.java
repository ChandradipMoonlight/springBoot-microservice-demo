package com.dailycode.ProductService.model;

import com.dailycode.ProductService.exceptions.ProductCustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private String type;
    private int statusCode;
}
