package fr.arolla.modec.service;

import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.ShippingServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ShippingServiceRepository shippingServiceRepository;
    private CartService cartService;
    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        Mockito.when(cartRepository.findById(Mockito.any())).thenReturn(Optional.of(cart));
        Mockito.when(shippingServiceRepository.findOneByCode("Chrono10")).thenReturn(new ShippingService("code", "Chrono10", "level"));
        cartService = new CartService(cartRepository, null, null, shippingServiceRepository);
    }

    @Test
    public void emptyCartShouldHaveNoShippingService() {
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Test
    public void FilledCartWithNoAddressShouldHaveNoShippingService() {
        CartLine line = new CartLine(new Sku("sku"), "name", new Quantity(1));
        cart.getLines().add(line);
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Test
    public void emptyCartWithShippingShouldHaveNoShippingService() {
        cart.setShippingAddress(new ShippingAddress("fullname", "line1", "city", "zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Test
    public void FilledCartWithShippingShouldHaveAShippingService() {
        CartLine line = new CartLine(new Sku("sku"), "name", new Quantity(1));
        cart.getLines().add(line);
        cart.setShippingAddress(new ShippingAddress("fullName", "line1", "city", "zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(new CartId()).size()).isEqualTo(1);
        assertThat(cartService.getShippingServices(new CartId()).get(0).getCarrier()).isEqualTo("Chrono10");
    }


}

