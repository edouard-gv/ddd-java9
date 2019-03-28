package fr.arolla.modec.web;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    private final ProductService product;

    @Autowired
    public HttpController(ProductService product) {
        this.product = product;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public Iterable<Product> productList() {
        return product.getList();
    }
}