package fr.arolla.modec.service;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getList() {
        return productRepository.findAll();
    }
}
