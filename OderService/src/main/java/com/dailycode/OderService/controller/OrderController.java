package com.dailycode.OderService.controller;

import com.dailycode.OderService.model.OrderRequest;
import com.dailycode.OderService.model.ResponseDTO;
import com.dailycode.OderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Inside OrderServiceController placeOrderService Method.");
        ResponseDTO response = new ResponseDTO("Order is placed successfully!",
                orderService.placeOrder(orderRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetails(@PathVariable("orderId") long orderId) {
        log.info("Inside getOrderDetails method of OrderController!");
        ResponseDTO response = new ResponseDTO("Order Fetched Successfully!",
                orderService.getOrderDetails(orderId));
        log.info(response.getMessage()+"\nOrderDetails : {}", response.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
