package fr.arolla.modec.service;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getList() {
        return productRepository.findAll();
    }
}
