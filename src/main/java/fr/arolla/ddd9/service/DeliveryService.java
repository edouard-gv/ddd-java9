package fr.arolla.ddd9.service;


import fr.arolla.ddd9.entity.DeliveryId;
import fr.arolla.ddd9.entity.OrderId;

public interface DeliveryService {

    DeliveryId createDeliveryFromOrder(OrderId orderId);
}
