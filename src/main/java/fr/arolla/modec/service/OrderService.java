package fr.arolla.modec.service;

import fr.arolla.modec.exception.BusinessException;
import fr.arolla.modec.entity.*;
import fr.arolla.modec.exception.CartNotFoundException;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.OrderLineRepository;
import fr.arolla.modec.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Transactional
    public OrderId createOrderFromCart(CartId cartId) throws BusinessException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        checkCartForOrder(cart);
        Order order = new Order(cart.getLines()
                .stream()
                .map(cartLine -> orderLineRepository.save(new OrderLine(cartLine.getProductSku(), cartLine.getProductName(), cartLine.getQuantity())))
                .collect(Collectors.toList()),
                clock.instant(),
                cart.getCustomer(),
                cart.getShippingAddress());
        order.setStatus(Order.Status.CREATED);
        order = orderRepository.save(order);
        return order.getId();
    }

    private void checkCartForOrder(Cart cart) throws BusinessException {
        if (cart.getCustomer() == null) {
            throw new BusinessException("Customer of order cannot be null");
        }
    }

    @Transactional
    public List<Order> getOrdersForEMail(String eMail) {
        return orderRepository.findByCustomerEmail(eMail);
    }
}
