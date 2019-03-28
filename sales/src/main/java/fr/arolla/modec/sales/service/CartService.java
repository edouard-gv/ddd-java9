package fr.arolla.modec.sales.service;

import fr.arolla.modec.sales.entity.*;
import fr.arolla.modec.sales.exception.CartNotFoundException;
import fr.arolla.modec.sales.repository.CartLineRepository;
import fr.arolla.modec.sales.repository.CartRepository;
import fr.arolla.modec.sales.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineRepository cartLineRepository;
    private final IAccessToLogistic logisticAccess;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartLineRepository cartLineRepository, IAccessToLogistic logisticAccess) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineRepository = cartLineRepository;
        this.logisticAccess = logisticAccess;
    }

    @Transactional
    public CartId createCart() {
        return cartRepository.save(new Cart()).getId();
    }

    @Transactional
    public void addToCart(CartId cartId, Sku sku, Quantity quantity) {
        Product product = productRepository.findOneBySku(sku);
        CartLine line = new CartLine(sku, product.getName(), quantity);
        cartLineRepository.save(line);
        findOrFail(cartId).getLines().add(line);
    }

    @Transactional
    public List<CartLine> getLines(CartId cartId) {
        // return a copy to load content
        return new ArrayList<>(findOrFail(cartId).getLines());
    }

    @Transactional
    public void setShippingAddress(CartId cartId, ShippingAddress shippingAddress) {
        findOrFail(cartId).setShippingAddress(shippingAddress);
    }

    @Transactional
    public void setRecipient(CartId cartId, Customer customer) {
        findOrFail(cartId).setCustomer(customer);
    }

    @Transactional
    public List<ShippingService> getShippingServices(CartId cartId) {
        return logisticAccess.getShippingServices(cartId);
    }

    private Cart findOrFail(CartId cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
    }
}
