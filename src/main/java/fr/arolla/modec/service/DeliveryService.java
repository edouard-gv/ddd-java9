package fr.arolla.modec.service;

import fr.arolla.modec.entity.DeliveryId;
import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;
import fr.arolla.modec.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DeliveryService {

    private final OrderRepository orderRepository;

    @Autowired
    public DeliveryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public DeliveryId createDeliveryFromOrder(OrderId orderId) {
        orderRepository.findById(orderId).get().setStatus(Order.Status.IN_PREPARATION);
        return new DeliveryId(orderId.getId());
    }
}
