package fr.arolla.modec.sales.entity;

import javax.persistence.*;

@Entity
public class CartLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(name = "productSku")
    Sku productSku;

    String productName;

    @Embedded
    Quantity quantity;

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
