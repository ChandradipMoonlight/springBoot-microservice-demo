package com.dailycode.OderService.external.client;

import com.dailycode.OderService.external.request.PaymentRequest;
import com.dailycode.OderService.model.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public ResponseEntity<ResponseDTO> doPayment(@RequestBody PaymentRequest paymentRequest);

    }
