package com.danielml.rubbyduckystore.application.service.order;

import com.danielml.rubbyduckystore.domain.models.Ducky;
import com.danielml.rubbyduckystore.domain.models.Order;
import com.danielml.rubbyduckystore.domain.ports.IDuckyRepository;
import com.danielml.rubbyduckystore.domain.ports.IOrderRepository;

public class DomainOrderService implements IOrderService {
    private final IDuckyRepository duckyRepository;
    private final IOrderRepository orderRepository;

    public DomainOrderService(IDuckyRepository duckyRepository, IOrderRepository orderRepository) {
        this.duckyRepository = duckyRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        Ducky ducky = this.duckyRepository.getDuckyById(order.getDuckyId());

        order.setDucky(ducky);
        order.applyPackaging();
        order.applyProtection();
        order.calculateTotalCost();
        order.applyDiscountByQuantity();
        order.calculateTotalCostByPackaging();
        order.calculateTotalCostByDestinationCountry();
        order.calculateTotalCostByShippingMode();

        return this.orderRepository.createOrder(order);
    }
}
