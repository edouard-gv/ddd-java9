package fr.arolla.modec;

import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.entity.Sku;
import fr.arolla.modec.sales.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        productRepository.save(new Product(new Sku("sku1"), "bike", "A bike for everyone!"));

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity("http://localhost:" + this.port + "/product", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //JSONAssert.assertEquals(cleanJson("[{'sku':{sku:'sku1'}, 'name':'bike', 'description':'A bike for everyone!'}]"),
        //        response.getBody(), false);

        JSONArray data = new JSONArray(response.getBody());
        assertThat(data.length()).isEqualTo(1);
        JSONObject product = data.getJSONObject(0);
        assertThat(product.has("sku")).isTrue();
        assertThat(product.getJSONObject("sku").has("sku")).isTrue();
        assertThat(product.getJSONObject("sku").getString("sku")).isEqualTo("sku1");
        assertThat(product.has("name")).isTrue();
        assertThat(product.getString("name")).isEqualTo("bike");
        assertThat(product.has("description")).isTrue();
        assertThat(product.getString("description")).isEqualTo("A bike for everyone!");
    }

    private static String cleanJson(String readableJSON) {
        return readableJSON.replace('\'', '"').replaceAll(", ", ",");
    }
}
