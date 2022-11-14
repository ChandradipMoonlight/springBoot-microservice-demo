package com.dailycode.PaymentService.service;

import com.dailycode.PaymentService.entity.TransactionDetails;
import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.PaymentResponse;
import com.dailycode.PaymentService.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentStatus("SUCCESS")
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();
        TransactionDetails savedTransaction = transactionRepository.save(transactionDetails);
        log.info("Transaction is completed : {}", transactionDetails);
        return modelMapper.map(savedTransaction, PaymentResponse.class);
    }
}
