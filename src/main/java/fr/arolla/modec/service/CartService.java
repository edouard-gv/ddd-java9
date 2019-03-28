package fr.arolla.modec.service;

import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartLineRepository;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.ProductRepository;
import fr.arolla.modec.repository.ShippingServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineRepository cartLineRepository;
    private final ShippingServiceRepository shippingServiceRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartLineRepository cartLineRepository, ShippingServiceRepository shippingServiceRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineRepository = cartLineRepository;
        this.shippingServiceRepository = shippingServiceRepository;
    }

    public CartId createCart() {
        return cartRepository.save(new Cart()).getId();
    }

    public void addToCart(CartId cartId, Sku sku, Quantity quantity) {
        Product product = productRepository.findOneBySku(sku);
        CartLine line = new CartLine(sku, product.getName(), quantity);
        cartLineRepository.save(line);
        findOrFail(cartId).getLines().add(line);
    }

    public List<CartLine> getLines(CartId cartId) {
        return findOrFail(cartId).getLines();
    }

    public void setShippingAddress(CartId cartId, ShippingAddress shippingAddress) {
        findOrFail(cartId).setShippingAddress(shippingAddress);
    }

    public List<ShippingService> getShippingServices(CartId cartId) {
        List<ShippingService> servicesFound = new ArrayList<>();
        Cart cart = findOrFail(cartId);
        if (cart.getShippingAddress() != null && !cart.getLines().isEmpty()) {
            Sku firstProductSku = cart.getLines().get(0).getProductSku();
            Product firstProduct = productRepository.findOneBySku(firstProductSku);
            if (firstProduct.getWeight().getWeight() >= 1) {
                servicesFound.add(shippingServiceRepository.findOneByCode("Chrono10"));
            }
            else {
                servicesFound.add(shippingServiceRepository.findOneByCode("laposte"));
            }
        }
        return servicesFound;
    }

    public void setRecipient(CartId cartId, Recipient recipient) {
        findOrFail(cartId).setRecipient(recipient);
    }

    private Cart findOrFail(CartId cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
    }
}
