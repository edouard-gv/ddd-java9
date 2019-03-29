package fr.arolla.modec.entity;

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
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
