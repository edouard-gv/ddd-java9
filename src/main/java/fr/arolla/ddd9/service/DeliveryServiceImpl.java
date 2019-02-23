package fr.arolla.ddd9.service;

import fr.arolla.ddd9.entity.DeliveryId;
import fr.arolla.ddd9.entity.Order;
import fr.arolla.ddd9.entity.OrderId;
import fr.arolla.ddd9.entity.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final OrderRepository orderRepository;

    @Autowired
    public DeliveryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public DeliveryId createDeliveryFromOrder(OrderId orderId) {
        orderRepository.findById(orderId).get().setStatus(Order.Status.IN_PREPARATION);
        return new DeliveryId(orderId.getId());
    }
}
