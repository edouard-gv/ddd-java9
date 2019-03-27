package fr.arolla.modec.service;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.arolla.modec.entity.*;
import fr.arolla.modec.repository.CartRepository;
import fr.arolla.modec.repository.ProductRepository;
import fr.arolla.modec.repository.ShippingServiceRepository;
import io.cucumber.datatable.DataTable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CartServiceStepDefs {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ShippingServiceRepository shippingServiceRepository;
    private CartService cartService;
    @Mock
    private ProductRepository productRepository;
    private Cart currentCart;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(cartRepository, productRepository, null, shippingServiceRepository);
    }

    @Given("^the following mocked shipping services:$")
    public void theFollowingMockedShippingServices(DataTable shippingServices) throws Throwable {
        List<Map<String, String>> lines = shippingServices.asMaps();
        for (Map<String, String> line : lines) {
            Mockito.when(shippingServiceRepository.findOneByCode(line.get("code"))).thenReturn(new ShippingService(line.get("code"), line.get("carrier"), line.get("level")));
        }
    }

    @Given("^the following mocked products:$")
    public void theFollowingMockedProducts(DataTable products) throws Throwable {
        List<Map<String, String>> lines = products.asMaps();
        for (Map<String, String> line : lines) {
            Mockito.when(productRepository.findOneBySku(new Sku(line.get("sku")))).thenReturn(
                    new Product(new Sku(line.get("sku")), line.get("name"),line.get("description"), new Weight(Double.parseDouble(line.get("weight")))));
        }
    }

    @Given("^a current Cart$")
    public void aCurrentCart() throws Throwable {
        currentCart = new Cart();
        Mockito.when(cartRepository.findById(Mockito.any())).thenReturn(Optional.of(currentCart));
    }

    @When("^the following products are added to the current cart:$")
    public void theFollowingProductsAreAddedToTheCurrentCart(DataTable products) throws Throwable {
        List<Map<String, String>> lines = products.asMaps();
        for (Map<String, String> line : lines) {
            CartLine cartLine = new CartLine(
                    new Sku(line.get("sku")),
                    line.get("name"),
                    new Quantity(Integer.valueOf(line.get("quantity")))
            );
            currentCart.getLines().add(cartLine);
        }
    }

    @When("^\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" in \"([^\"]*)\" is set as the shipping address of current cart$")
    public void inIsSetAsTheShippingAddressOfCurrentCart(String fullName, String line1, String city, String zipCode, String isoCountryCode) throws Throwable {
        currentCart.setShippingAddress(new ShippingAddress(fullName, line1, city, zipCode, isoCountryCode));

    }

    @Then("^shipping service for the current cart should be empty$")
    public void shippingServiceForTheCurrentCartShouldBeEmpty() throws Throwable {
        assertThat(cartService.getShippingServices(new CartId())).isEmpty();
    }

    @Then("^the shipping services available for the current cart should be:$")
    public void theShippingServicesAvailableForTheCurrentCartShouldBe(DataTable shippingServices) throws Throwable {
        List<Map<String, String>> actualList = new ArrayList<>();

        for (ShippingService shippingService : cartService.getShippingServices(new CartId())) {
            Map<String, String> shippingServiceMap = new HashMap<>();
            shippingServiceMap.put("code", shippingService.getCode());
            shippingServiceMap.put("label", shippingService.getCarrier() + " " + shippingService.getLevel());
            actualList.add(shippingServiceMap);
        }
        assertThat(actualList).isEqualTo(shippingServices.asMaps());
    }
}
