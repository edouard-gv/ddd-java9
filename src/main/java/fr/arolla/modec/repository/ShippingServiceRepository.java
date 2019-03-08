package fr.arolla.modec.repository;

import fr.arolla.modec.entity.ShippingService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepository extends CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
