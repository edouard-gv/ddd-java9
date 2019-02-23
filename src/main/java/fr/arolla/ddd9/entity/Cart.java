package fr.arolla.ddd9.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(CartId.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<CartLine> lines;

    @Embedded
    private ShippingAddress shippingAddress;

    @Embedded
    private Recipient recipient;

    public Cart() {
        lines = new ArrayList<>();
    }

    public CartId getId() {
        return new CartId(id);
    }

    public List<CartLine> getLines() {
        return lines;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
