package com.dailycode.OderService.external.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    public enum ExceptionType {
        PRODUCT_NOT_FOUND,
        INSUFFICIENT_QUANTITY,
        INTERNAL_SERVER_ERROR,
    }

    private String msg;
    private CustomException.ExceptionType type;

    public CustomException(String msg){
        this.msg=msg;
    }

    public CustomException(String msg, ExceptionType type) {
        super(msg);
        this.type=type;
    }

}