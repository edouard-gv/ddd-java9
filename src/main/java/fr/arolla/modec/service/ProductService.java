package fr.arolla.modec.service;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Iterable<Product> getList() {
        return productRepository.findAll();
    }
}
