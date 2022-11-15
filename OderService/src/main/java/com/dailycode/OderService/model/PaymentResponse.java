package com.dailycode.OderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private long transactionId;
    private String referenceNumber;
    private Instant paymentDate;
    private String paymentStatus;
    private PaymentMode paymentMode;
    private long amount;
}
