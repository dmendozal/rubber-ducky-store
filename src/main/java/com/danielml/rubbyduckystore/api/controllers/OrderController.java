package com.danielml.rubbyduckystore.api.controllers;

import com.danielml.rubbyduckystore.api.mappers.OrderMapper;
import com.danielml.rubbyduckystore.api.models.orders.CreateOrderRequest;
import com.danielml.rubbyduckystore.api.models.orders.CreateOrderResult;
import com.danielml.rubbyduckystore.application.service.order.IOrderService;
import com.danielml.rubbyduckystore.domain.models.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    private final IOrderService orderService;
    public OrderController(IOrderService OrderService) {
        this.orderService = OrderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResult> createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws Exception {
        Order order = OrderMapper.toOrder(createOrderRequest);

        return new ResponseEntity<>(OrderMapper.toOrderResult(this.orderService.createOrder(order)), HttpStatus.CREATED);
    }
}
