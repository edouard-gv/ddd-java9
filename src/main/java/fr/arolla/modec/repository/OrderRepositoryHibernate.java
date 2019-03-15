package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryHibernate extends OrderRepository, CrudRepository<Order, OrderId> {
    List<Order> findByRecipientEmail(String eMail);
}
