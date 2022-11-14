package com.dailycode.PaymentService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse extends PaymentRequest {
    private long transactionId;
    private Instant paymentDate;
    private String paymentStatus;
}
