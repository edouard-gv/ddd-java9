package fr.arolla.modec.sales.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

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
                "sku='" + sku + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sku)) return false;
        Sku sku1 = (Sku) o;
        return Objects.equals(sku, sku1.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
