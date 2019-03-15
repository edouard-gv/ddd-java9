package fr.arolla.modec;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.entity.Sku;
import fr.arolla.modec.entity.Weight;
import fr.arolla.modec.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing web API, starting the server
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITest {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productList() throws Exception {
        productRepository.save(new Product(new Sku("sku1"), "bike", "A bike for everyone!", new Weight(10)));

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity("http://localhost:" + this.port + "/product", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONAssert.assertEquals(cleanJson("[{'sku':{sku:'sku1'}, 'name':'bike', 'description':'A bike for everyone!'}]"),
                response.getBody(), false);
    }

    private static String cleanJson(String readableJSON) {
        return readableJSON.replace('\'', '"').replaceAll(", ", ",");
    }
}
