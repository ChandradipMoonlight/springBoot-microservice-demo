package com.dailycode.OderService.service;

import com.dailycode.OderService.model.OrderRequest;
import com.dailycode.OderService.model.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
