package fr.arolla.modec.logistic.domain;

public class Quantity {
    private long quantity;

    public long getQuantity() {
        return quantity;
    }

    public Quantity() { //for JPA
    }

    public Quantity(long quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return Long.toString(quantity);
    }
}
