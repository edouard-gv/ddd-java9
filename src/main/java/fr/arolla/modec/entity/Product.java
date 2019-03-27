package fr.arolla.modec.entity;

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

    @Embedded
    private Weight weight;

    public Sku getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Weight getWeight() {
        return weight;
    }

    public Product() { //for JPA
    }

    public Product(Sku sku, String name, String description, Weight weight) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
}
