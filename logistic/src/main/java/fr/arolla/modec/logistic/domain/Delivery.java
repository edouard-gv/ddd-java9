package fr.arolla.modec.logistic.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(DeliveryId.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<DeliveryLine> lines;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    public Delivery() { // for JPA
    }

    public Delivery(List<DeliveryLine> lines, Contact contact, Address address) {
        this.lines = lines;
        this.contact = contact;
        this.address = address;
    }

    public DeliveryId getId() {
        return new DeliveryId(id);
    }

    public List<DeliveryLine> getLines() {
        return lines;
    }

    public Address getAddress() {
        return address;
    }
}
