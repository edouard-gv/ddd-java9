package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.ShippingService;

public interface ShippingServiceRepository {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
