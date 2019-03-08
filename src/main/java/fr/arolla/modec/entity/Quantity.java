package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {
    long value;

    public long getValue() {
        return value;
    }

    public Quantity() { //for JPA
    }

    public Quantity(long value) {
        if (value < 0) throw new java.lang.IllegalArgumentException("Quantity must be positive: " + value);
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
