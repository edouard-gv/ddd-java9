package fr.arolla.modec.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"Order\"")
@IdClass(OrderId.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<OrderLine> lines;

    private Status status;

    private Instant creationDate;

    @Embedded
    private ShippingAddress shippingAddress;

    @Embedded
    private Customer customer;

    public Order() { // for JPA
    }

    public Order(List<OrderLine> lines, Instant creationDate, Customer customer, ShippingAddress shippingAddress) {
        this.lines = lines;
        this.creationDate = creationDate;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }

    public OrderId getId() {
        return new OrderId(id);
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public enum Status {
        CREATED, IN_PREPARATION;

        public String toString() {
            return name().toLowerCase().replaceAll("_", " ");
        }
    }
}
