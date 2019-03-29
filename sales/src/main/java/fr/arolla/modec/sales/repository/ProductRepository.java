package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.entity.Sku;

public interface ProductRepository {
    Product findOneBySku(Sku sku);

    Iterable<Product> findAll();

    void deleteAll();

    Product save(Product product);
}
