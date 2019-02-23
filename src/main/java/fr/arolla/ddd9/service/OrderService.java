package fr.arolla.ddd9.service;

import fr.arolla.ddd9.BusinessException;
import fr.arolla.ddd9.entity.CartId;
import fr.arolla.ddd9.entity.Order;
import fr.arolla.ddd9.entity.OrderId;

import java.util.List;

public interface OrderService {

    OrderId createOrderFromCart(CartId cartId) throws BusinessException;

    List<Order> getOrdersForEMail(String eMail);
}
