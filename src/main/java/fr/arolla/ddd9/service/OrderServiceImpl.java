package fr.arolla.ddd9.service;

import fr.arolla.ddd9.BusinessException;
import fr.arolla.ddd9.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final DateService dateService;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Autowired
    public OrderServiceImpl(CartRepository cartRepository, DateService dateService, OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.cartRepository = cartRepository;
        this.dateService = dateService;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public OrderId createOrderFromCart(CartId cartId) throws BusinessException {
        Cart cart = cartRepository.findById(cartId).get();
        checkCartForOrder(cart);
        Order order = new Order(cart.getLines()
                .stream()
                .map(cartLine -> orderLineRepository.save(new OrderLine(cartLine.getProductSku(), cartLine.getProductName(), cartLine.getQuantity())))
                .collect(Collectors.toList()),
                dateService.getCurrentDate(),
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

    @Override
    public List<Order> getOrdersForEMail(String eMail) {
        return orderRepository.findByRecipientEmail(eMail);
    }
}
