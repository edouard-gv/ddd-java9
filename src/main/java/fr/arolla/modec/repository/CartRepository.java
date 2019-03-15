package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Cart;
import fr.arolla.modec.entity.CartId;

import java.util.Optional;

public interface CartRepository {
    Cart save(Cart cart);

    Optional<Cart> findById(CartId cartId);
}
