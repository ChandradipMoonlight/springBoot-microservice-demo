package com.dailycode.ProductService.controller;

import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.model.ResponseDTO;
import com.dailycode.ProductService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Log4j2
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

    @GetMapping("/allProduct")
    public ResponseEntity<ResponseDTO> getAllProducts() {

        List<ProductResponse> allProducts = productService.getAllProducts();
        ResponseDTO responseDTO = new ResponseDTO("All Products are Fetched Successfully!",
                allProducts);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/reduceProductQuantity/{productId}")
    public ResponseEntity<ResponseDTO> reduceProductQuantity(@PathVariable("productId") long productId,
                                                            @RequestParam long productQuantity) {
        ResponseDTO responseDTO = new ResponseDTO("Quantity After order is placed",
                productService.reduceQuantity(productId, productQuantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateProductQuantity/{productId}")
    public ResponseEntity<ResponseDTO> updateProductQuantity(@PathVariable("productId") long productId,
                                                             @RequestParam long productQuantity) {
        ResponseDTO response = new ResponseDTO("Product Quantity is updated Successfully!",
                productService.updateProductQuantity(productId, productQuantity));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
