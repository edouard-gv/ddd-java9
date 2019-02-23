package fr.arolla.ddd9.entity;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    @Column(unique = true, nullable = false)
    private String sku;

    public String getSku() {
        return sku;
    }

    public Product() { //for JPA
    }

    public Product(String sku, String name, String description) {
        this.sku = sku;
        this.name = name;
        this.description = description;
    }
}
