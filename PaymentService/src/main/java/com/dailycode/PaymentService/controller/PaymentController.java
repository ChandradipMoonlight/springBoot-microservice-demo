package com.dailycode.PaymentService.controller;

import com.dailycode.PaymentService.model.PaymentRequest;
import com.dailycode.PaymentService.model.ResponseDTO;
import com.dailycode.PaymentService.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Log4j2
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<ResponseDTO> doPayment(@RequestBody PaymentRequest paymentRequest) {
        ResponseDTO response = new ResponseDTO("Payment Is Done Successfully!",
                paymentService.doPayment(paymentRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO> getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId){
        log.info("Inside getPaymentDetailsByOrderId() method of PaymentController Class!");
        ResponseDTO responseDTO = new ResponseDTO("Payment Details fetched successfully!",
                paymentService.getPaymentDetailsByOrderId(orderId));
        log.info(responseDTO.getMessage()+": {}", responseDTO.getData());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
}
