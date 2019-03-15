package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Cart;
import fr.arolla.modec.entity.CartId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public interface CartRepository {
    Cart save(Cart cart);

    Optional<Cart> findById(CartId cartId);
}
