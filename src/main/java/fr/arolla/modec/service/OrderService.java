package fr.arolla.modec.service;

import fr.arolla.modec.BusinessException;
import fr.arolla.modec.entity.CartId;
import fr.arolla.modec.entity.Order;
import fr.arolla.modec.entity.OrderId;

import java.util.List;

public interface OrderService {

    OrderId createOrderFromCart(CartId cartId) throws BusinessException;

    List<Order> getOrdersForEMail(String eMail);
}
