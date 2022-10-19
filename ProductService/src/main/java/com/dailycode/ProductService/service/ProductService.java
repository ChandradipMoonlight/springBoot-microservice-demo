package com.dailycode.ProductService.service;

import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.model.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    long reduceQuantity(long productId, long productQuantity);

    List<ProductResponse> getAllProducts();
}
