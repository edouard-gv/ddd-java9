package fr.arolla.modec.exception;

import fr.arolla.modec.entity.CartId;

/**
 */
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(CartId cartId) {
        super("cartId: " + cartId.getId());
    }
}
