package fr.arolla.modec.acceptance;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import fr.arolla.modec.entity.*;
import fr.arolla.modec.exception.BusinessException;
import fr.arolla.modec.repository.ProductRepository;
import fr.arolla.modec.repository.ShippingServiceRepository;
import fr.arolla.modec.service.CartService;
import fr.arolla.modec.service.DeliveryService;
import fr.arolla.modec.service.OrderService;
import fr.arolla.modec.service.ProductService;
import fr.arolla.modec.util.FixedButMutableClock;
import io.cucumber.datatable.DataTable;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class StepDefs extends SpringBootBaseStepDefs {

    private ProductRepository productRepository;
    private ProductService productService;
    private CartService cartService;
    private FixedButMutableClock clock;
    private ShippingServiceRepository shippingServiceRepository;
    private OrderService orderService;
    private DeliveryService deliveryService;
    private CartId currentCartId;
    private OrderId currentOrderId;
    private DeliveryId currentDeliveryId;

    public StepDefs(ProductRepository productRepository,
                    ProductService productService,
                    FixedButMutableClock clock,
                    ShippingServiceRepository shippingServiceRepository,
                    CartService cartService,
                    OrderService orderService,
                    DeliveryService deliveryService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.clock = clock;
        this.shippingServiceRepository = shippingServiceRepository;
        this.orderService = orderService;
        this.deliveryService = deliveryService;
    }

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public Clock clock() {
            return new FixedButMutableClock(Instant.now(), ZoneId.systemDefault());
        }
    }

    public Product productEntry(Map<String, String> entry) {
        return new Product(new Sku(entry.get("SKU")), entry.get("name"), entry.get("description"), new Weight(Float.parseFloat(entry.get("weight"))));
    }

    public ShippingService shippingServiceEntry(Map<String, String> entry) {
        return new ShippingService(entry.get("code"), entry.get("carrier"), entry.get("level"));
    }

    @Given("the default background")
    public void theDefaultBackground() {
        productRepository.deleteAll();
        shippingServiceRepository.deleteAll();
        productRepository.save(new Product(new Sku("7612345678900"), "bike", "A default bike", new Weight(10)));
        productRepository.save(new Product(new Sku("7612345678101"), "feather", "A light product", new Weight(0.1)));
        shippingServiceRepository.save(new ShippingService("Chrono10", "Chronopost", "j+1 avant 13h"));
        shippingServiceRepository.save(new ShippingService("laposte", "La Poste", "Standard"));
        Instant instant = Instant.from(ZonedDateTime.parse("2018-10-08T13:00:00+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        clock.setInstant(instant);
    }

    @Then("now should be {string}")
    public void nowShouldBe(String stringDate) {
        assertThat(clock.instant()).isEqualTo(Instant.from(ZonedDateTime.parse(stringDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }

    @Then("{string} should be the default locale")
    public void shouldBeTheDefaultLocale(String arg0) {
        //local feature not implemented
    }

    @Then("the following products should be in the catalog, and no others:")
    public void theFollowingProductsShouldBeInTheCatalogAndNoOthers(DataTable expectedProducts) {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (Product product : productService.getList()) {
            Map<String, String> productMap = new HashMap<>();
            productMap.put("SKU", product.getSku().getSku());
            productMap.put("description", product.getDescription());
            productMap.put("name", product.getName());
            productMap.put("weight", ""+product.getWeight().getWeight());
            actualList.add(productMap);
        }
        assertThat(actualList).isEqualTo(expectedProducts.asMaps());
    }

    @Then("following shipping services should be declared, and no others:")
    public void followingShippingServicesShouldBeDeclaredAndNoOthers(DataTable shippingServices) {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (ShippingService shippingService : shippingServiceRepository.findAll()) {
            Map<String, String> shippingServiceMap = new HashMap<>();
            shippingServiceMap.put("code", shippingService.getCode());
            shippingServiceMap.put("label", shippingService.getCarrier() + " " + shippingService.getLevel());
            actualList.add(shippingServiceMap);
        }
        assertThat(actualList).isEqualTo(shippingServices.asMaps());
    }

    @Given("^now is \"([^\"]*)\"$")
    public void nowIs(String stringDate) {
        Instant instant = Instant.from(ZonedDateTime.parse(stringDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        clock.setInstant(instant);
    }

    @Given("^\"([^\"]*)\" as default locale$")
    public void asDefaultLocale(String arg0) {
        //Locale features not implemented
    }

    @Given("^the following catalog:$")
    public void theFollowingCatalog(DataTable products) {
        productRepository.deleteAll();
        for (Map<String, String> productEntry : products.asMaps()) {
            productRepository.save(productEntry(productEntry));
        }
    }

    @Given("^the following shipping services:$")
    public void theFollowingShippingServices(DataTable shippingServices) {
        shippingServiceRepository.deleteAll();
        for (Map<String, String> shippingServiceEntry : shippingServices.asMaps()) {
            shippingServiceRepository.save(shippingServiceEntry(shippingServiceEntry));
        }
    }

    @Given("^a new cart is created$")
    public void aNewCartIsCreated() {
        this.currentCartId = cartService.createCart();
    }

    @Given("^product \"([^\"]*)\" is added to this cart$")
    public void productIsAddedToThisCart(String sku) {
        cartService.addToCart(this.currentCartId, new Sku(sku), new Quantity(1));
    }

    @Given("^\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in \"([^\"]*)\" is set as the shipping address of this cart$")
    public void inIsSetAsTheShippingAddressOfThisCart(String fullName, String line1, String city, String zipCode, String isoCountryCode) {
        ShippingAddress shippingAddress = new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode);
        cartService.setShippingAddress(this.currentCartId, shippingAddress);
    }

    @Transactional
    @Then("^cart content should contain:$")
    public void cartContentShouldContain(DataTable cartLines) {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (CartLine cartLine : cartService.getLines(this.currentCartId)) {
            Map<String, String> cartLineMap = new HashMap<>();
            cartLineMap.put("SKU", cartLine.getProductSku().getSku());
            cartLineMap.put("name", cartLine.getProductName());
            cartLineMap.put("quantity", cartLine.getQuantity().toString());
            actualList.add(cartLineMap);
        }
        assertThat(actualList).isEqualTo(cartLines.asMaps());
    }

    @Then("^the shipping services available for this cart should be:$")
    public void theShippingServicesAvailableForThisCartShouldBe(DataTable shippingServices) {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (ShippingService shippingService : cartService.getShippingServices(this.currentCartId)) {
            Map<String, String> shippingServiceMap = new HashMap<>();
            shippingServiceMap.put("code", shippingService.getCode());
            shippingServiceMap.put("label", shippingService.getCarrier() + " " + shippingService.getLevel());
            actualList.add(shippingServiceMap);
        }
        assertThat(actualList).isEqualTo(shippingServices.asMaps());
    }

    @And("^\"([^\"]*)\" with email address \"([^\"]*)\" is set as the recipient$")
    public void withEmailAdressIsSetAsTheRecipient(String fullName, String eMail) {
        Customer customer = new Customer(fullName, eMail);
        cartService.setCustomer(currentCartId, customer);
    }

    @And("^order is validated$")
    public void orderIsValidated() throws Throwable {
        currentOrderId = orderService.createOrderFromCart(this.currentCartId);
    }

    @And("^order should not be validated$")
    public void orderCannotBeValidated() {
        assertThatThrownBy(() -> orderService.createOrderFromCart(currentCartId)).isInstanceOf(BusinessException.class);
    }

    @Then("^the historical orders for recipient \"([^\"]*)\" should be:$")
    public void theHistoricalOrdersForRecipientShouldBe(String eMail, DataTable orders) {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (Order order : orderService.getOrdersForEMail(eMail)) {
            Map<String, String> orderMap = new HashMap<>();
            orderMap.put("creation date", DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault()).format(order.getCreationDate()));
            orderMap.put("status", order.getStatus().toString());
            actualList.add(orderMap);
        }
        assertThat(actualList).isEqualTo(orders.asMaps());
    }

    @And("^delivery is validated$")
    public void deliveryIsValidated() {
        currentDeliveryId = deliveryService.createDeliveryFromOrder(currentOrderId);
    }
}
