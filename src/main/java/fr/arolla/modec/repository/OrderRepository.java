package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;
import fr.arolla.modec.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public interface OrderRepository {
    List<Order> findByRecipientEmail(String eMail);

    Optional<Order> findById(OrderId orderId);

    Order save(Order order);
}
