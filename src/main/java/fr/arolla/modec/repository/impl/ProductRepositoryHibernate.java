package fr.arolla.modec.repository.impl;

import fr.arolla.modec.entity.Product;
import fr.arolla.modec.entity.Sku;
import fr.arolla.modec.repository.ProductRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryHibernate extends ProductRepository, CrudRepository<Product, Long> {
    Product findOneBySku(Sku sku);
}
