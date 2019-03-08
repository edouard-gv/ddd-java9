package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Cart;
import fr.arolla.modec.entity.CartId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository {

    public Optional<Cart> findById(CartId cartId);

    public Cart save(Cart cart);
}
