package fr.arolla.ddd9.service;

import fr.arolla.ddd9.entity.CartId;
import fr.arolla.ddd9.entity.CartLine;
import fr.arolla.ddd9.entity.Quantity;
import fr.arolla.ddd9.entity.ShippingService;

import java.util.List;

public interface CartService {

    CartId createCart();

    void addToCart(CartId cartId, String sku, Quantity quantity);

    List<CartLine> getLines(CartId cartId);

    void setShippingAddress(CartId cartId, String fullName, String line1, String city, String zipCode, String countryIsoCode);

    List<ShippingService> getShippingServices(CartId cartId);

    void setRecipient(CartId cartId, String fullName, String eMail);
}
