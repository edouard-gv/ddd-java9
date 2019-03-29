package fr.arolla.modec.logistic.entity;

import javax.persistence.*;

@Entity
public class Deliverable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true, nullable = false)
    private Sku sku;

    private String name;

    @Embedded
    private Weight weight;

    public Sku getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Weight getWeight() {
        return weight;
    }

    public Deliverable() { //for JPA
    }

    public Deliverable(Sku sku, String name, Weight weight) {
        this.sku = sku;
        this.name = name;
        this.weight = weight;
    }
}
