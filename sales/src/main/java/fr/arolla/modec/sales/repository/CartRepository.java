package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.Cart;
import fr.arolla.modec.sales.entity.CartId;

import java.util.Optional;

public interface CartRepository {
    Cart save(Cart cart);

    Optional<Cart> findById(CartId cartId);
}
