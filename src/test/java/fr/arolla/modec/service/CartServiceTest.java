package fr.arolla.modec.service;

import fr.arolla.modec.entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CartServiceTest {

    private MockCartRepository cartRepository;
    private CartService cartService;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cartRepository = new MockCartRepository();
        cartService = new CartServiceImpl(cartRepository, null, null, null);
        cart = new Cart();
        cartRepository.setHardCodedCart(cart);
    }

    @Test
    public void emptyCartShouldHaveNoShippingService() {
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Test
    public void FilledCartWithNoAddressShouldHaveNoShippingService() {
        CartLine line = new CartLine("sku", "name", new Quantity(1));
        cart.getLines().add(line);
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Test
    public void emptyCartWithShippingShouldHaveNoShippingService() {
        cart.setShippingAddress(new ShippingAddress("fullname", "line1", "city","zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }
}

