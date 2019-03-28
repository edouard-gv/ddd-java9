package fr.arolla.modec.sales.entity;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true, nullable = false)
    private Sku sku;

    private String name;

    private String description;

    public Product() { //for JPA
    }

    public Product(Sku sku, String name, String description) {
        this.sku = sku;
        this.name = name;
        this.description = description;
    }

    public Sku getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
