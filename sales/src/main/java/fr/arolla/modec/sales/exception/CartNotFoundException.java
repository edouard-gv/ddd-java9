package fr.arolla.modec.sales.exception;

import fr.arolla.modec.sales.entity.CartId;

/**
 */
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(CartId cartId) {
        super("cartId: " + cartId.getId());
    }
}
