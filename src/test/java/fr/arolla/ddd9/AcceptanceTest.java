package fr.arolla.ddd9;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/fr/arolla/ddd9/acceptance"
)

public class AcceptanceTest {

}
