package fr.arolla.ddd9.service;

import fr.arolla.ddd9.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineRepository cartLineRepository;
    private final ShippingServiceRepository shippingServiceRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartLineRepository cartLineRepository, ShippingServiceRepository shippingServiceRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineRepository = cartLineRepository;
        this.shippingServiceRepository = shippingServiceRepository;
    }

    @Override
    public CartId createCart() {
        return cartRepository.save(new Cart()).getId();
    }

    @Override
    public void addToCart(CartId cartId, String sku, Quantity quantity) {
        Product product = productRepository.findOneBySku(sku);
        CartLine line = new CartLine(sku, product.getName(), quantity);
        cartLineRepository.save(line);
        cartRepository.findById(cartId).get().getLines().add(line);
    }

    @Override
    public List<CartLine> getLines(CartId cartId) {
        return cartRepository.findById(cartId).get().getLines();
    }

    @Override
    public void setShippingAddress(CartId cartId, String fullName, String line1, String city, String zipCode, String isoCountryCode) {
        ShippingAddress address = new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode);
        cartRepository.findById(cartId).get().setShippingAddress(address);
    }

    @Override
    public List<ShippingService> getShippingServices(CartId cartId) {
        List<ShippingService> servicesFound = new ArrayList<>();
        if (cartRepository.findById(cartId).get().getShippingAddress() != null) {
            servicesFound.add(shippingServiceRepository.findOneByCode("Chrono10"));
        }
        return servicesFound;
    }

    @Override
    public void setRecipient(CartId cartId, String fullName, String eMail) {
        Recipient recipient = new Recipient(fullName, eMail);
        cartRepository.findById(cartId).get().setRecipient(recipient);
    }
}
