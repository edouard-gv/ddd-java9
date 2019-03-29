package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.Order;
import fr.arolla.modec.sales.entity.OrderId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findByCustomerEmail(String email);

    Optional<Order> findById(OrderId orderId);

    Order save(Order order);
}
