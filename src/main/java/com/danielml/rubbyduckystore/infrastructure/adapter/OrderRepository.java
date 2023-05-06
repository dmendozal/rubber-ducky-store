package com.danielml.rubbyduckystore.infrastructure.adapter;

import com.danielml.rubbyduckystore.api.mappers.OrderMapper;
import com.danielml.rubbyduckystore.domain.models.Order;
import com.danielml.rubbyduckystore.domain.ports.IOrderRepository;
import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderRepository implements IOrderRepository {

    private final IOrderCrudRepository orderCrudRepository;

    public OrderRepository(IOrderCrudRepository orderCrudRepository) {
        this.orderCrudRepository = orderCrudRepository;
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = OrderMapper.toOrderEntity(order);
        Order orderSaved = OrderMapper.toOrderModel(this.orderCrudRepository.save(orderEntity));
        orderSaved.setDetails(order.getDetails());

        return orderSaved;
    }
}
