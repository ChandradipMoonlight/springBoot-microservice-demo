package com.dailycode.OderService.external.decoder;

import com.dailycode.OderService.external.exception.CustomException;
import com.dailycode.OderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Exception decode(String s, Response response) {
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());
        try {
            ErrorResponse errorResponse = modelMapper.map(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(errorResponse.getMessage(), CustomException.ExceptionType.INSUFFICIENT_QUANTITY);
        } catch (IOException e) {
            throw new CustomException("Internal Server Error", CustomException.ExceptionType.INTERNAL_SERVER_ERROR);
        }
    }
}
