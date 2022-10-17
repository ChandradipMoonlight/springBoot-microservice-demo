package com.dailycode.ProductService.controller;

import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.model.ResponseDTO;
import com.dailycode.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody ProductRequest productRequest) {
        ResponseDTO responseDTO = new ResponseDTO("product is added",
                productService.addProduct(productRequest));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable("productId") long productId){
        ResponseDTO response = new ResponseDTO("Product is Fetched!",
                productService.getProductById(productId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
