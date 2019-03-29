package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.Order;
import fr.arolla.modec.sales.entity.OrderId;
import fr.arolla.modec.sales.repository.OrderRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryHibernate extends OrderRepository, CrudRepository<Order, OrderId> {
    List<Order> findByCustomerEmail(String email);
}
