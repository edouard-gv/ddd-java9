package fr.arolla.modec.entity;

import javax.persistence.*;

@Entity
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String productSku;
    String productName;

    @Embedded
    Quantity quantity;

    public CartLine() { //for JPA
    }

    public CartLine(String productSku, String productName, Quantity quantity) {
        this.productSku = productSku;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductSku() {
        return productSku;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
