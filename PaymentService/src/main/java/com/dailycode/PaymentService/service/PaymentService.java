package com.dailycode.PaymentService.service;

import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.PaymentResponse;

public interface PaymentService {
    PaymentResponse doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
