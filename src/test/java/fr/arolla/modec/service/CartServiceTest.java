package fr.arolla.modec.service;

import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.ProductRepository;
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
    @Mock
    private ProductRepository productRepository;
    private CartService cartService;
    private Cart cart;
    private CartId cartId = null;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        Mockito.when(shippingServiceRepository.findOneByCode("Chrono10")).thenReturn(new ShippingService("Chrono10", "Chronopost", "level"));
        Mockito.when(shippingServiceRepository.findOneByCode("laposte")).thenReturn(new ShippingService("laposte", "La Poste", "level"));
        Mockito.when(productRepository.findOneBySku(new Sku("sku1"))).thenReturn(new Product(new Sku("sku"), "Heavy","A heavy product", new Weight(10)));
        Mockito.when(productRepository.findOneBySku(new Sku("sku2"))).thenReturn(new Product(new Sku("sku"), "Light","A light product", new Weight(0.5)));
        cartService = new CartService(cartRepository, productRepository, null, shippingServiceRepository);
    }

    @Test
    public void emptyCartShouldHaveNoShippingService() {
        assertThat(cartService.getShippingServices(cartId)).isEmpty();
    }

    @Test
    public void FilledCartWithNoAddressShouldHaveNoShippingService() {
        CartLine line = new CartLine(new Sku("sku1"), "name", new Quantity(1));
        cart.getLines().add(line);
        assertThat(cartService.getShippingServices(cartId)).isEmpty();
    }

    @Test
    public void emptyCartWithShippingShouldHaveNoShippingService() {
        cart.setShippingAddress(new ShippingAddress("fullname", "line1", "city", "zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(cartId)).isEmpty();
    }

    @Test
    public void FilledCartWithShippingShouldHaveAShippingService() {
        CartLine line = new CartLine(new Sku("sku1"), "name", new Quantity(1));
        cart.getLines().add(line);
        cart.setShippingAddress(new ShippingAddress("fullName", "line1", "city", "zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(cartId).size()).isEqualTo(1);
        assertThat(cartService.getShippingServices(cartId).get(0).getCarrier()).isEqualTo("Chronopost");
    }

    @Test
    public void LightCartShouldHaveLaPosteShippingService() {
        CartLine line = new CartLine(new Sku("sku2"), "name", new Quantity(1));
        cart.getLines().add(line);
        cart.setShippingAddress(new ShippingAddress("fullName", "line1", "city", "zipCode", "isoCountryCode"));
        assertThat(cartService.getShippingServices(cartId).size()).isEqualTo(1);
        assertThat(cartService.getShippingServices(cartId).get(0).getCarrier()).isEqualTo("La Poste");
    }
}

