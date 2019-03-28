package fr.arolla.modec.service;

import fr.arolla.modec.entity.DeliveryId;
import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;
import fr.arolla.modec.exception.OrderNotFoundException;
import fr.arolla.modec.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryService {

    private final OrderRepository orderRepository;

    public DeliveryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public DeliveryId createDeliveryFromOrder(OrderId orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId)).setStatus(Order.Status.IN_PREPARATION);
        return new DeliveryId(orderId.getId());
    }
}
