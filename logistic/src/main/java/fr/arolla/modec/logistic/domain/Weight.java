package fr.arolla.modec.logistic.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Weight {
    private double weight;

    public Weight() {
    }

    public Weight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
