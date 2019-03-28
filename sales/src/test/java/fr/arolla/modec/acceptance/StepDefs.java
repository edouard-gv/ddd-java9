package fr.arolla.modec.acceptance;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Deliverables;
import fr.arolla.modec.logistic.domain.DeliveryId;
import fr.arolla.modec.logistic.domain.ShippingServices;
import fr.arolla.modec.logistic.domain.Weight;
import fr.arolla.modec.sales.exception.BusinessException;
import fr.arolla.modec.sales.entity.Quantity;
import fr.arolla.modec.sales.entity.Sku;
import fr.arolla.modec.sales.entity.*;
import fr.arolla.modec.sales.repository.*;
import fr.arolla.modec.sales.service.CartService;
import fr.arolla.modec.sales.service.IAccessToLogistic;
import fr.arolla.modec.sales.service.OrderService;
import fr.arolla.modec.sales.service.ProductService;
import fr.arolla.modec.sales.util.FixedButMutableClock;
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
    private ShippingServices shippingServices;
    private OrderService orderService;
    private IAccessToLogistic logisticAccess;
    private CartId currentCartId;
    private OrderId currentOrderId;
    private Deliverables deliverables;
    private DeliveryId currentDeliveryId;

    public StepDefs(ProductRepository productRepository,
                    ProductService productService,
                    Deliverables deliverables,
                    FixedButMutableClock clock,
                    ShippingServices shippingServices,
                    CartService cartService,
                    OrderService orderService,
                    IAccessToLogistic logisticAccess) {
        this.productRepository = productRepository;
        this.deliverables = deliverables;
        this.productService = productService;
        this.logisticAccess = logisticAccess;
        this.cartService = cartService;
        this.clock = clock;
        this.shippingServices = shippingServices;
        this.orderService = orderService;
    }

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public Clock clock() {
            return new FixedButMutableClock(Instant.now(), ZoneId.systemDefault());
        }
    }

    @Given("^now is \"([^\"]*)\"$")
    public void nowIs(String stringDate) throws Throwable {
        Instant instant = Instant.from(ZonedDateTime.parse(stringDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        clock.setInstant(instant);
    }

    @Given("^\"([^\"]*)\" as default locale$")
    public void asDefaultLocale(String arg0) throws Throwable {
        //Locale features not implemented
    }

    @Given("^the following catalog:$")
    public void theFollowingCatalog(DataTable products) throws Throwable {
        productRepository.deleteAll();
        deliverables.deleteAll();
        List<Map<String, String>> lines = products.asMaps();
        for (Map<String, String> line : lines) {
            productRepository.save(new Product(new Sku(line.get("SKU")), line.get("name"), line.get("description")));
            deliverables.save(new Deliverable(
                    new fr.arolla.modec.logistic.domain.Sku(line.get("SKU")),
                    line.get("name"),
                    new Weight(Float.parseFloat(line.get("weight")))
            ));
        }
    }

    @Given("^the following shipping services:$")
    public void theFollowingShippingServices(DataTable shippingServices) throws Throwable {
        this.shippingServices.deleteAll();
        List<Map<String, String>> lines = shippingServices.asMaps();
        for (Map<String, String> line : lines) {
            this.shippingServices.save(new fr.arolla.modec.logistic.domain.ShippingService(line.get("code"), line.get("carrier"), line.get("level")));
        }
    }

    @Given("^a new cart is created$")
    public void aNewCartIsCreated() throws Throwable {
        this.currentCartId = cartService.createCart();
    }

    @Given("^product \"([^\"]*)\" is added to this cart$")
    public void productIsAddedToThisCart(String sku) throws Throwable {
        cartService.addToCart(this.currentCartId, new Sku(sku), new Quantity(1));
    }

    @Given("^\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in \"([^\"]*)\" is set as the shipping address of this cart$")
    public void inIsSetAsTheShippingAddressOfThisCart(String fullName, String line1, String city, String zipCode, String isoCountryCode) throws Throwable {
        ShippingAddress shippingAddress = new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode);
        cartService.setShippingAddress(this.currentCartId, shippingAddress);
    }

    @Then("^product catalog should contain:$")
    public void productCatalogShouldContain(DataTable products) throws Throwable {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (Product product : productService.getList()) {
            Map<String, String> productMap = new HashMap<>();
            productMap.put("SKU", product.getSku().getSku());
            productMap.put("name", product.getName());
            actualList.add(productMap);
        }
        assertThat(actualList).isEqualTo(products.asMaps());
    }

    @Then("^cart content should contain:$")
    public void cartContentShouldContain(DataTable cartLines) throws Throwable {
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
    public void theShippingServicesAvailableForThisCartShouldBe(DataTable shippingServices) throws Throwable {
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
    public void withEmailAdressIsSetAsTheRecipient(String fullName, String eMail) throws Throwable {
        Customer customer = new Customer(fullName, eMail);
        cartService.setRecipient(currentCartId, customer);
    }

    @And("^order is validated$")
    public void orderIsValidated() throws Throwable {
        currentOrderId = orderService.createOrderFromCart(this.currentCartId);
    }

    @And("^order should not be validated$")
    public void orderCannotBeValidated() throws Throwable {
        assertThatThrownBy(() -> orderService.createOrderFromCart(currentCartId)).isInstanceOf(BusinessException.class);
    }

    @Then("^the historical orders for recipient \"([^\"]*)\" should be:$")
    public void theHistoricalOrdersForRecipientShouldBe(String eMail, DataTable orders) throws Throwable {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (Order order : orderService.getOrdersForEMail(eMail)) {
            Map<String, String> orderMap = new HashMap<>();
            orderMap.put("creation date", DateTimeFormatter.ofPattern("YYYY-MM-dd").withZone(ZoneId.systemDefault()).format(order.getCreationDate()));
            orderMap.put("status", order.getStatus().toString());
            actualList.add(orderMap);
        }
        assertThat(actualList).isEqualTo(orders.asMaps());
    }

    @And("^delivery is validated$")
    public void deliveryIsValidated() throws Throwable {
        orderService.orderIsPrepared(currentOrderId);
    }
}
