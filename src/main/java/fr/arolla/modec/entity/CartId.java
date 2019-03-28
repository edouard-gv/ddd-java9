package fr.arolla.modec.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartId implements Serializable {

    private Long id;

    public CartId(Long id) {
        this.id = id;
    }

    public CartId() {//for JPA
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartId)) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(id, cartId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
