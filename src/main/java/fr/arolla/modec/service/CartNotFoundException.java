package fr.arolla.modec.service;

import fr.arolla.modec.entity.CartId;

/**
 */
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(CartId cartId) {
        super("cartId: " + cartId.getId());
    }
}
