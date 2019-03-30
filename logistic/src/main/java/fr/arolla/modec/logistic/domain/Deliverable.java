package fr.arolla.modec.logistic.domain;

public class Deliverable {
    private Long id;

    private Sku sku;

    private String name;

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

    public Deliverable(Sku sku, String name, Weight weight) {
        this.sku = sku;
        this.name = name;
        this.weight = weight;
    }
}
