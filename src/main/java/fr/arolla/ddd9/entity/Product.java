package fr.arolla.ddd9.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sku;

    public String getSku() {
        return sku;
    }

    public Product(String sku) {
        this.sku = sku;
    }

    public Product() { //for JPA
    }
}
