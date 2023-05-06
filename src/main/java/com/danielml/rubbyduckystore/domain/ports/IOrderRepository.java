package com.danielml.rubbyduckystore.domain.ports;

import com.danielml.rubbyduckystore.domain.models.Order;

public interface IOrderRepository {
    Order createOrder(Order order);
}
