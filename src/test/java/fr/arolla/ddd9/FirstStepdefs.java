package fr.arolla.ddd9;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;


public class FirstStepdefs {
    @When("^product catalog is called$")
    public void productCatalogIsCalled() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^should be returned a product list containing:$")
    public void shouldBeReturnedAProductListContainingElements(DataTable arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a new cart is created$")
    public void aNewCartIsCreated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^should be returned a cart item list containing:$")
    public void shouldBeReturnedACartContaining(DataTable arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^\"([^\"]*)\" as default locale$")
    public void asDefaultLocale(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the following catalog:$")
    public void theFollowingCatalog(DataTable arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the following shipping services:$")
    public void theFollowingShippingServices() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^product (\\d+) is added to this cart$")
    public void productIsAddedToThisCart(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^cart content is asked$")
    public void cartContentIsAsked() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^\"([^\"]*)\" in \"([^\"]*)\" is set as the shipping address of this cart$")
    public void inIsSetAsTheShippingAddressOfThisCart(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the shipping services available for this cart is asked$")
    public void theShippingServicesAvailableForThisCartIsAsked() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^should be returned the following shopping services:$")
    public void shouldBeReturnedTheFollowingShoppingServices() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
