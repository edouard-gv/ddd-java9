package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.entity.Sku;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    Product findOneBySku(Sku sku);

    Iterable<Product> findAll();

    void deleteAll();

    Product save(Product product);
}
