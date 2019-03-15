package fr.arolla.modec.repository;

import fr.arolla.modec.entity.ShippingService;

public interface ShippingServiceRepository {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
