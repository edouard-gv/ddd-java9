package fr.arolla.modec.service;


import fr.arolla.modec.entity.DeliveryId;
import fr.arolla.modec.entity.OrderId;

public interface DeliveryService {

    DeliveryId createDeliveryFromOrder(OrderId orderId);
}
