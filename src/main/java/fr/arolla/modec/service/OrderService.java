package fr.arolla.modec.service;

import fr.arolla.modec.BusinessException;
import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.OrderLineRepository;
import fr.arolla.modec.repository.OrderRepository;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final CartRepository cartRepository;
    private final Clock clock;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderService(CartRepository cartRepository, Clock clock, OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.cartRepository = cartRepository;
        this.clock = clock;
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
                clock.instant(),
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
