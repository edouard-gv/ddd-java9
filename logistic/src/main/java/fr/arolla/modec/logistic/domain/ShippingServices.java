package fr.arolla.modec.logistic.domain;

public interface ShippingServices {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
