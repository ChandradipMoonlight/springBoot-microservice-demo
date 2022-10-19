package com.dailycode.ProductService.exceptions;
import lombok.Data;
@Data
public class ProductCustomException extends RuntimeException {

    public enum ExceptionType {
        PRODUCT_NOT_FOUND,
        PRODUCT_ALREADY_PRESENT, OUT_OF_STOCK, INSUFFICIENT_QUANTITY,
    }

    private String msg;
    private ProductCustomException.ExceptionType type;

    public ProductCustomException(String msg) {
        this.msg=msg;
    }
    public ProductCustomException(String message, ExceptionType type){
        super(message);
        this.type=type;
    }
}
