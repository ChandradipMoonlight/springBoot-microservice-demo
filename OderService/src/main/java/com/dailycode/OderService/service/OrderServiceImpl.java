package com.dailycode.OderService.service;

import com.dailycode.OderService.entity.Orders;
import com.dailycode.OderService.external.client.PaymentService;
import com.dailycode.OderService.external.client.ProductService;
import com.dailycode.OderService.external.exception.CustomException;
import com.dailycode.OderService.external.request.PaymentRequest;
import com.dailycode.OderService.model.OrderRequest;
import com.dailycode.OderService.model.OrderResponse;
import com.dailycode.OderService.model.ProductResponse;
import com.dailycode.OderService.model.ResponseDTO;
import com.dailycode.OderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentService paymentService;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //Order entity -> Save the data with status order created
        //Product Service -> Block Products(reduce the quantity)
        //payment Service -> Payments -> Success -> COMPLETED else CANCELLED
        log.info("Placing order request: {}", orderRequest);
        productService.reduceProductQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating order with status created!");
        Orders createOrder = modelMapper.map(orderRequest, Orders.class);
        createOrder.setOrderStatus("CREATED");
        Orders saveOrder = orderRepository.save(createOrder);
        log.info("Calling PaymentService to complete the payment service!");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(createOrder.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getAmount())
                .build();
        String orderStatus = null;
        try {
             paymentService.doPayment(paymentRequest);
            log.info("Payment Done SuccessFully Changing the Order Status to PLACED!");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error Occurred in payment, Changing status to FAILd!");
            orderStatus = "PAYMENT-FAILED";
        }
        createOrder.setOrderStatus(orderStatus);
        saveOrder = orderRepository.save(createOrder);

       log.info("Order is placed Successfully! : {}", saveOrder);

        OrderResponse orderResponse = modelMapper.map(saveOrder, OrderResponse.class);
        orderResponse.setPaymentMode(paymentRequest.getPaymentMode());
        return orderResponse;
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Inside getOrderDetails method of OrderService class!");
        log.info("Getting OrderDetails for OrderId : {}", orderId);
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order is not found for given Id : " + orderId,
                        CustomException.ExceptionType.ORDER_NOT_FOUND));
        log.info("Invoking ProductService to fetch the productDetails for Id : {}", orders.getProductId());
        ResponseDTO responseDTO = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/"+orders.getProductId(),
                ResponseDTO.class);
        ProductResponse productResponse = modelMapper.map(responseDTO.getData(), ProductResponse.class);
        log.info("Product Details: {}", productResponse);
        OrderResponse orderResponse = modelMapper.map(orders, OrderResponse.class);
        orderResponse.setProductResponse(productResponse);
        return orderResponse;
    }
}
