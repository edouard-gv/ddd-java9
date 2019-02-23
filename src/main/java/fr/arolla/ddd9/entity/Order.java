package fr.arolla.ddd9.entity;

import javax.persistence.*;
import java.util.Calendar;
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

    private Calendar creationDate;

    @Embedded
    private ShippingAddress shippingAddress;

    @Embedded
    private Recipient recipient;

    public Order() { // for JPA
    }

    public Order(List<OrderLine> lines, Calendar creationDate, Recipient recipient, ShippingAddress shippingAddress) {
        this.lines = lines;
        this.creationDate = creationDate;
        this.recipient = recipient;
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public enum Status {
        CREATED, IN_PREPARATION;

        public String toString() {
            return super.toString().toLowerCase().replaceAll("_", " ");
        }
    }
}
