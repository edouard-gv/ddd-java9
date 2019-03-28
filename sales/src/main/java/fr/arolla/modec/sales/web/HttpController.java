package fr.arolla.modec.sales.web;

import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.repository.ProductRepository;
import fr.arolla.modec.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    private final ProductService product;

    @Autowired
    public HttpController(ProductRepository productRepository) {
        this.product = new ProductService(productRepository);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public Iterable<Product> productList() {
        return product.getList();
    }
}