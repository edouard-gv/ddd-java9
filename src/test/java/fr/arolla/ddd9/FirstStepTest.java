package fr.arolla.ddd9;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //@TODO: comprendre pourquoi je suis obligé de mettre le chemin local...
        features = "src/test/java/fr/arolla/ddd9"
)

public class FirstStepTest {

}
