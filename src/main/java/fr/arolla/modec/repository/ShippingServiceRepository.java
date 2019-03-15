package fr.arolla.modec.repository;

import fr.arolla.modec.entity.ShippingService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepository {
    ShippingService findOneByCode(String code);

    void deleteAll();

    ShippingService save(ShippingService shippingService);
}
