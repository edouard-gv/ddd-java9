package fr.arolla.modec.logistic.repository;

import fr.arolla.modec.logistic.entity.ShippingService;

public interface ShippingServiceRepository {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
