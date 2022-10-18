package com.dailycode.OderService.service;

import com.dailycode.OderService.entity.Orders;
import com.dailycode.OderService.model.OrderRequest;
import com.dailycode.OderService.model.OrderResponse;
import com.dailycode.OderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        log.info("Inside OrderService class placeOrder Method!");
        Orders createOrder = modelMapper.map(orderRequest, Orders.class);
        createOrder.setOrderStatus("CREATED");
        Orders saveOrder = orderRepository.save(createOrder);
       log.info("Order is placed Successfully! : {}", saveOrder);
        return modelMapper.map(saveOrder, OrderResponse.class);
    }
}
