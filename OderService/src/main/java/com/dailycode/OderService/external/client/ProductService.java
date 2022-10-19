package com.dailycode.OderService.external.client;

import com.dailycode.OderService.model.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceProductQuantity/{productId}")
    ResponseEntity<ResponseDTO> reduceProductQuantity(@PathVariable("productId") long productId,
                                                      @RequestParam long productQuantity);
}
