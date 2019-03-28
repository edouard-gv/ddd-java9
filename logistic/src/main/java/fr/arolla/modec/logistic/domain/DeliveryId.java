package fr.arolla.modec.logistic.domain;

import java.io.Serializable;
import java.util.Objects;

public class DeliveryId implements Serializable {
    private Long id;

    public DeliveryId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryId)) return false;
        DeliveryId that = (DeliveryId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
