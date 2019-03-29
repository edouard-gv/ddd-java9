package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findByCustomerEmail(String email);

    Optional<Order> findById(OrderId orderId);

    Order save(Order order);
}
