package com.dailycode.OderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse{

    private long orderId;
    private long productId;
    private long quantity;
    private long amount;
    private Instant orderDate;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private ProductResponse productResponse;
    private PaymentResponse paymentResponse;
}
