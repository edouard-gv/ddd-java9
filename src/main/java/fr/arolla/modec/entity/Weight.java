package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Weight {
    private float weight;

    public Weight() {
    }

    public Weight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "quantity=" + weight +
                '}';
    }
}
