package com.danielml.rubbyduckystore.application.service.order;

import com.danielml.rubbyduckystore.domain.models.Order;

public interface IOrderService {
    Order createOrder(Order order) throws Exception;
}