package fr.arolla.modec.sales.service;

import fr.arolla.modec.sales.entity.CartId;
import fr.arolla.modec.sales.entity.ShippingService;

import java.util.List;

public interface IAccessToLogistic {
    List<ShippingService> getShippingServices(CartId cartId);
}
