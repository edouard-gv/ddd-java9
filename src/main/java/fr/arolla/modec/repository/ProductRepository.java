package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findOneBySku(String sku);
}
