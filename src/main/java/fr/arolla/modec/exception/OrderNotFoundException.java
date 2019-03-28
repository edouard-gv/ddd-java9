package fr.arolla.modec.exception;

import fr.arolla.modec.entity.OrderId;

/**
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId orderId) {
        super("orderId: " + orderId.getId());
    }
}
