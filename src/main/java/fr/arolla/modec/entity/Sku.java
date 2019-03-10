package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Sku {
    private String sku;

    public Sku() { //for JPA
    }

    public Sku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "quantity='" + sku + '\'' +
                '}';
    }
}
