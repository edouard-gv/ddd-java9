package fr.arolla.modec.sales.exception;

import fr.arolla.modec.sales.entity.OrderId;

/**
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId orderId) {
        super("orderId: " + orderId.getId());
    }
}
