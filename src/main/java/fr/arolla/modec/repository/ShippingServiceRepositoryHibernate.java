package fr.arolla.modec.repository;

import fr.arolla.modec.entity.ShippingService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepositoryHibernate extends ShippingServiceRepository, CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
