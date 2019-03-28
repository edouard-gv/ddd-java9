package fr.arolla.modec.entity;

import javax.persistence.*;

@Entity
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(name = "productSku")
    private Sku productSku;

    private String productName;

    @Embedded
    private Quantity quantity;

    public CartLine() { //for JPA
    }

    public CartLine(Sku productSku, String productName, Quantity quantity) {
        this.productSku = productSku;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Sku getProductSku() {
        return productSku;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
