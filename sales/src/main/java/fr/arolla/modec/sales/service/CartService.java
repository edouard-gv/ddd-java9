package fr.arolla.modec.sales.service;

import fr.arolla.modec.logistic.domain.*;
import fr.arolla.modec.logistic.domain.service.ShippingServicesCalculator;
import fr.arolla.modec.sales.entity.Quantity;
import fr.arolla.modec.sales.entity.Sku;
import fr.arolla.modec.sales.entity.*;
import fr.arolla.modec.sales.repository.CartLineRepository;
import fr.arolla.modec.sales.repository.CartRepository;
import fr.arolla.modec.sales.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineRepository cartLineRepository;
    private final ShippingServicesCalculator shippingServicesCalculator;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartLineRepository cartLineRepository, ShippingServicesCalculator shippingServicesCalculator) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineRepository = cartLineRepository;
        this.shippingServicesCalculator = shippingServicesCalculator;
    }

    public CartId createCart() {
        return cartRepository.save(new Cart()).getId();
    }

    public void addToCart(CartId cartId, Sku sku, Quantity quantity) {
        Product product = productRepository.findOneBySku(sku);
        CartLine line = new CartLine(sku, product.getName(), quantity);
        cartLineRepository.save(line);
        cartRepository.findById(cartId).get().getLines().add(line);
    }

    public List<CartLine> getLines(CartId cartId) {
        return cartRepository.findById(cartId).get().getLines();
    }

    public void setShippingAddress(CartId cartId, String fullName, String line1, String city, String zipCode, String isoCountryCode) {
        ShippingAddress address = new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode);
        cartRepository.findById(cartId).get().setShippingAddress(address);
    }

    public List<ShippingService> getShippingServices(CartId cartId) {
        List<ShippingService> servicesFound = new ArrayList<>();
        Cart cart = cartRepository.findById(cartId).get();
        Delivery delivery = buildDeliveryFromCart(cart);
        return shippingServicesCalculator.calculate(delivery);
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

    public void setRecipient(CartId cartId, String fullName, String eMail) {
        Customer customer = new Customer(fullName, eMail);
        cartRepository.findById(cartId).get().setCustomer(customer);
    }
}
