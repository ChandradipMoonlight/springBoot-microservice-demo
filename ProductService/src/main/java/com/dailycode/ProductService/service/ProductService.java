package com.dailycode.ProductService.service;

import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.model.ProductRequest;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
