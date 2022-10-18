package com.dailycode.OderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse extends OrderRequest {

    private long orderId;
    private Instant orderDate;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
