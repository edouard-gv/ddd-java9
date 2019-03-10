package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {
    long quantity;

    public long getQuantity() {
        return quantity;
    }

    public Quantity() { //for JPA
    }

    public Quantity(long quantity) {
        if (quantity < 0) throw new java.lang.IllegalArgumentException("Quantity must be positive: " + quantity);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return Long.toString(quantity);
    }
}
