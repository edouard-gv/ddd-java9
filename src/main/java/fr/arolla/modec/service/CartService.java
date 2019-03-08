package fr.arolla.modec.service;

import fr.arolla.modec.entity.CartId;
import fr.arolla.modec.entity.CartLine;
import fr.arolla.modec.entity.Quantity;
import fr.arolla.modec.entity.ShippingService;

import java.util.List;

public interface CartService {

    CartId createCart();

    void addToCart(CartId cartId, String sku, Quantity quantity);

    List<CartLine> getLines(CartId cartId);

    void setShippingAddress(CartId cartId, String fullName, String line1, String city, String zipCode, String countryIsoCode);

    List<ShippingService> getShippingServices(CartId cartId);

    void setRecipient(CartId cartId, String fullName, String eMail);
}
