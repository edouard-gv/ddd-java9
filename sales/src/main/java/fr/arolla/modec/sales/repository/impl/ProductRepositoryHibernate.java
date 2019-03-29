package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.entity.Sku;
import fr.arolla.modec.sales.repository.ProductRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryHibernate extends ProductRepository, CrudRepository<Product, Long> {
    Product findOneBySku(Sku sku);
}
