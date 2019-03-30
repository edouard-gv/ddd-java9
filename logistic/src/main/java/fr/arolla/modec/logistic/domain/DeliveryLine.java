package fr.arolla.modec.logistic.domain;

public class DeliveryLine {
    private Long id;

    private Sku deliverableSku;
    private String productName;

    private Quantity quantity;

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
