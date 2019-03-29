package fr.arolla.modec.sales.service;

import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getList() {
        return productRepository.findAll();
    }
}
