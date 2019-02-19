package fr.arolla.ddd9.service;

import fr.arolla.ddd9.entity.Product;

public interface ProductService {
    Iterable<Product> getList();
}
