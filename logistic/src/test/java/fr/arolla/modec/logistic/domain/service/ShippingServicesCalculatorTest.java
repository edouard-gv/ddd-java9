package fr.arolla.modec.logistic.domain.service;

import fr.arolla.modec.logistic.domain.*;
import fr.arolla.modec.logistic.domain.Deliverables;
import fr.arolla.modec.logistic.domain.ShippingServices;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ShippingServicesCalculatorTest {

    @Mock
    private ShippingServices shippingServices;
    @Mock
    private Deliverables deliverables;
    private ShippingServicesCalculator shippingServicesCalculator;

    @Before
    public void setUp() throws Exception {
        Mockito.when(shippingServices.findOneByCode("Chrono10")).thenReturn(new ShippingService("Chrono10", "Chronopost", "level"));
        Mockito.when(shippingServices.findOneByCode("laposte")).thenReturn(new ShippingService("laposte", "La Poste", "level"));
        Mockito.when(deliverables.findOneBySku(new Sku("sku1"))).thenReturn(new Deliverable(new Sku("sku"), "Heavy", new Weight(10)));
        Mockito.when(deliverables.findOneBySku(new Sku("sku2"))).thenReturn(new Deliverable(new Sku("sku"), "Light", new Weight(0.5)));
        shippingServicesCalculator = new ShippingServicesCalculator(shippingServices, deliverables);
    }

    @Test
    public void emptyCartShouldHaveNoShippingService() {
        Delivery delivery = new Delivery(new ArrayList<>(), null, null);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery)).isEmpty();
    }

    @Test
    public void FilledCartWithNoAddressShouldHaveNoShippingService() {
        Delivery delivery = new Delivery(new ArrayList<>(), null, null);
        DeliveryLine line = new DeliveryLine(new Sku("sku1"), "name", new Quantity(1));
        delivery.getLines().add(line);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery)).isEmpty();
    }

    @Test
    public void emptyCartWithAddressShouldHaveNoShippingService() {
        Delivery delivery = new Delivery(new ArrayList<>(), null, new Address("fullname", "line1", "city", "zipCode", "isoCountryCode"));
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery)).isEmpty();
    }

    @Test
    public void FilledCartWithShippingShouldHaveAShippingService() {
        Delivery delivery = new Delivery(new ArrayList<>(), null, new Address("fullname", "line1", "city", "zipCode", "isoCountryCode"));
        DeliveryLine line = new DeliveryLine(new Sku("sku1"), "name", new Quantity(1));
        delivery.getLines().add(line);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery).size()).isEqualTo(1);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery).get(0).getCarrier()).isEqualTo("Chronopost");
    }

    @Test
    public void LightCartShouldHaveLaPosteShippingService() {
        Delivery delivery = new Delivery(new ArrayList<>(), null, new Address("fullname", "line1", "city", "zipCode", "isoCountryCode"));
        DeliveryLine line = new DeliveryLine(new Sku("sku2"), "name", new Quantity(1));
        delivery.getLines().add(line);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery).size()).isEqualTo(1);
        Assertions.assertThat(shippingServicesCalculator.calculate(delivery).get(0).getCarrier()).isEqualTo("La Poste");
    }
}

