package com.dailycode.OderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private long productId;
    private long quantity;
    private long amount;
    private PaymentMode paymentMode;
}
