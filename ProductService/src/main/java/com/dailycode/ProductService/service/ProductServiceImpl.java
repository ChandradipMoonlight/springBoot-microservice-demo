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

import java.util.List;
import java.util.stream.Collectors;

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
        log.info("Inside getProductById() method of ProductService class!");
        log.info("Getting Product Details for given Id : {}",productId);
        Product product = productRepo.findById(productId)
                .orElseThrow(()->new ProductCustomException("Product Not Found with Id given Id",
                        ProductCustomException.ExceptionType.PRODUCT_NOT_FOUND));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public long reduceQuantity(long productId, long productQuantity) {
        log.info("Reduce quantity {} for Id : {}", productQuantity, productId);
        Product product = productRepo.findById(productId)
                .orElseThrow(()->new ProductCustomException("Product is not found with given Id",
                        ProductCustomException.ExceptionType.PRODUCT_NOT_FOUND));
        long remainingQuantity;
        if (product.getQuantity()>=productQuantity) {
            remainingQuantity = product.getQuantity()-productQuantity;
            product.setQuantity(remainingQuantity);
            productRepo.save(product);
        } else {
            throw new ProductCustomException("Product does not have sufficient quantity!",
                    ProductCustomException.ExceptionType.INSUFFICIENT_QUANTITY);
        }
        log.info("Reduced Quantity after Order is placed : {}", remainingQuantity);
        return remainingQuantity;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = productRepo.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public ProductResponse updateProductQuantity(long productId, long productQuantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(()->new ProductCustomException("Product is not found with given Id",
                        ProductCustomException.ExceptionType.PRODUCT_NOT_FOUND));
        product.setQuantity(productQuantity);
        productRepo.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }
}
