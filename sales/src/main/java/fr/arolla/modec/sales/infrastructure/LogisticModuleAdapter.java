package fr.arolla.modec.sales.infrastructure;

import fr.arolla.modec.logistic.domain.Address;
import fr.arolla.modec.logistic.domain.Contact;
import fr.arolla.modec.logistic.domain.Delivery;
import fr.arolla.modec.logistic.domain.DeliveryLine;
import fr.arolla.modec.logistic.domain.ICalculateShippingServices;
import fr.arolla.modec.sales.entity.Cart;
import fr.arolla.modec.sales.entity.CartId;
import fr.arolla.modec.sales.entity.ShippingService;
import fr.arolla.modec.sales.repository.CartRepository;
import fr.arolla.modec.sales.service.IAccessToLogistic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogisticModuleAdapter implements IAccessToLogistic {

    private CartRepository cartRepository;
    private ICalculateShippingServices shippingServicesCalculator;

    public LogisticModuleAdapter(CartRepository cartRepository, ICalculateShippingServices shippingServicesCalculator) {
        this.cartRepository = cartRepository;
        this.shippingServicesCalculator = shippingServicesCalculator;
    }

    public List<ShippingService> getShippingServices(CartId cartId) {
        List<ShippingService> servicesFound = new ArrayList<>();
        Cart cart = cartRepository.findById(cartId).get();
        Delivery delivery = buildDeliveryFromCart(cart);
        return shippingServicesCalculator.calculate(delivery)
                .stream()
                .map(logisticSercice -> new ShippingService(
                        logisticSercice.getCode(),
                        logisticSercice.getCarrier(),
                        logisticSercice.getLevel()))
                .collect(Collectors.toList());
    }

    private Delivery buildDeliveryFromCart(Cart cart) {
        return new Delivery(cart.getLines()
                .stream()
                .map(cartLine -> new DeliveryLine(
                        new fr.arolla.modec.logistic.domain.Sku(cartLine.getProductSku().getSku()),
                        cartLine.getProductName(),
                        new fr.arolla.modec.logistic.domain.Quantity(cartLine.getQuantity().getQuantity())))
                .collect(Collectors.toList()),
                cart.getCustomer() != null ? new Contact(cart.getCustomer().getEmail()) : null,
                cart.getShippingAddress() != null ? new Address(
                        cart.getShippingAddress().getRecipientName(),
                        cart.getShippingAddress().getLine1(),
                        cart.getShippingAddress().getCity(),
                        cart.getShippingAddress().getZipCode(),
                        cart.getShippingAddress().getIsoCountryCode()
                ) : null);
    }
}
