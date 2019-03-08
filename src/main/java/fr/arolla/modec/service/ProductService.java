package fr.arolla.modec.service;

import fr.arolla.modec.entity.Product;

public interface ProductService {
    Iterable<Product> getList();
}
