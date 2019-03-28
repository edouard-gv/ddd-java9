package fr.arolla.modec.acceptance;

import fr.arolla.modec.util.FixedButMutableClock;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
public class SpringBootBaseStepDefs {

}
