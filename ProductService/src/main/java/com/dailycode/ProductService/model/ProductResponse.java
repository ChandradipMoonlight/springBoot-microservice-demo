package com.dailycode.ProductService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends ProductRequest{
    private long productId;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
