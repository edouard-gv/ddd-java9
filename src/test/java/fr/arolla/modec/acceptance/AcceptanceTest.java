package fr.arolla.modec.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/fr/arolla/modec/acceptance"
)

public class AcceptanceTest {

}
