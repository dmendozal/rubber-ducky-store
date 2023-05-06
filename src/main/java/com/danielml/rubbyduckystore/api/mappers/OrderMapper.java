package com.danielml.rubbyduckystore.api.mappers;

import com.danielml.rubbyduckystore.api.models.orders.CreateOrderRequest;
import com.danielml.rubbyduckystore.api.models.orders.CreateOrderResult;
import com.danielml.rubbyduckystore.domain.models.Order;
import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.OrderEntity;

public class OrderMapper {
    public static Order toOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setDuckyId(createOrderRequest.duckyId());
        order.setQuantity(createOrderRequest.quantity());
        order.setDestinationCountry(createOrderRequest.country());
        order.setShippingMode(createOrderRequest.shippingMode());

        return order;
    }

    public static OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(order.getId(),
                order.getDestinationCountry(),
                order.getShippingMode(),
                order.getPackagingType(),
                order.getProtectionType(),
                order.getQuantity(),
                order.isHasMoistureAbsorbingBalls(),
                order.getTotal(),
                DuckyMapper.toDuckyEntity(order.getDucky()));
    }

    public static Order toOrderModel(OrderEntity orderEntity) {
        return Order.create(orderEntity.getId(),
                orderEntity.getDestinationCountry(),
                orderEntity.getShippingMode(),
                orderEntity.getPackagingType(),
                orderEntity.getProtectionType(),
                orderEntity.getQuantity(),
                orderEntity.isHasMoistureAbsorbingBalls(),
                orderEntity.getTotal(),
                orderEntity.getDucky().getId());
    }

    public static CreateOrderResult toOrderResult(Order order) {
        return new CreateOrderResult(order.getPackagingType(),
                order.getProtectionType(),
                order.isHasMoistureAbsorbingBalls(),
                order.getTotal(),
                order.getDetails());
    }
}
