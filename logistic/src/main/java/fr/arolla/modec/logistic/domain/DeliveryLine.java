package fr.arolla.modec.logistic.domain;

import javax.persistence.*;

@Entity
public class DeliveryLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    Sku deliverableSku;
    String productName;

    @Embedded
    Quantity quantity;

    public DeliveryLine() { //for JPA
    }

    public DeliveryLine(Sku deliverableSku, String productName, Quantity quantity) {
        this.deliverableSku = deliverableSku;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Sku getDeliverableSku() {
        return deliverableSku;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
