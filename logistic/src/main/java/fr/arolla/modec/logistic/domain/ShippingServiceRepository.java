package fr.arolla.modec.logistic.domain;

import fr.arolla.modec.logistic.domain.ShippingService;

public interface ShippingServiceRepository {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
