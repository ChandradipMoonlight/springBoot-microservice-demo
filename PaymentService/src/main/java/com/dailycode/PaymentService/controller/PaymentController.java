package com.dailycode.PaymentService.controller;

import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.ResponseDTO;
import com.dailycode.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<ResponseDTO> doPayment(@RequestBody PaymentRequest paymentRequest) {
        ResponseDTO response = new ResponseDTO("Payment Is Done Successfully!",
                paymentService.doPayment(paymentRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
