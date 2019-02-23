package fr.arolla.ddd9.service;

import fr.arolla.ddd9.entity.Product;
import fr.arolla.ddd9.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getList() {
        return productRepository.findAll();
    }
}
