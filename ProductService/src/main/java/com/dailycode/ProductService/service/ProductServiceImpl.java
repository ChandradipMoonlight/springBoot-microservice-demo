package com.dailycode.ProductService.service;

import com.dailycode.ProductService.entity.Product;
import com.dailycode.ProductService.exceptions.ProductCustomException;
import com.dailycode.ProductService.model.ProductResponse;
import com.dailycode.ProductService.model.ProductRequest;
import com.dailycode.ProductService.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepo productRepo;
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        log.info("Inside addProduct Method of Product Service Class.");
        Product add = productRepo.save(modelMapper.map(productRequest, Product.class));
        return modelMapper.map(add, ProductResponse.class);
    }

    @Override
    public ProductResponse getProductById(long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(()->new ProductCustomException("Product Not Found with Id given Id",
                        ProductCustomException.ExceptionType.PRODUCT_NOT_FOUND));
        return modelMapper.map(product, ProductResponse.class);
    }
}
