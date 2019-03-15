package fr.arolla.modec.service;

import fr.arolla.modec.BusinessException;
import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.OrderLineRepository;
import fr.arolla.modec.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final CartRepository cartRepository;
    private final Timestamp timestamp;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Autowired
    public OrderService(CartRepository cartRepository, Timestamp timestamp, OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.cartRepository = cartRepository;
        this.timestamp = timestamp;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public OrderId createOrderFromCart(CartId cartId) throws BusinessException {
        Cart cart = cartRepository.findById(cartId).get();
        checkCartForOrder(cart);
        Order order = new Order(cart.getLines()
                .stream()
                .map(cartLine -> orderLineRepository.save(new OrderLine(cartLine.getProductSku(), cartLine.getProductName(), cartLine.getQuantity())))
                .collect(Collectors.toList()),
                timestamp.getCurrentDate(),
                cart.getRecipient(),
                cart.getShippingAddress());
        order.setStatus(Order.Status.CREATED);
        order = orderRepository.save(order);
        return order.getId();
    }

    private void checkCartForOrder(Cart cart) throws BusinessException {
        if (cart.getRecipient() == null) {
            throw new BusinessException("Recipient of order cannot be null");
        }
    }

    public List<Order> getOrdersForEMail(String eMail) {
        return orderRepository.findByRecipientEmail(eMail);
    }
}
