package fr.arolla.modec.logistic.domain;

import java.util.List;

public class Delivery {

    private Long id;

    private List<DeliveryLine> lines;

    private Address address;

    private Contact contact;

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
