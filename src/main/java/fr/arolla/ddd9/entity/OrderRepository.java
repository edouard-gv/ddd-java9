package fr.arolla.ddd9.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, OrderId> {
    List<Order> findByRecipientEmail(String eMail);
}
