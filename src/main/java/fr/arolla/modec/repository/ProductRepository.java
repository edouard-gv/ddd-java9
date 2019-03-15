package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.entity.Sku;

public interface ProductRepository {
    Product findOneBySku(Sku sku);

    Iterable<Product> findAll();

    void deleteAll();

    Product save(Product product);
}
