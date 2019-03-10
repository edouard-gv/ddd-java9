package fr.arolla.modec.service;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/fr/arolla/modec/service"
)

public class CartServiceFeaturedTest {

}
