package fr.arolla.modec.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DeliveryId {
    private Long id;

    public DeliveryId() { //for JPA
    }

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
