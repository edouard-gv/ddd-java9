package fr.arolla.modec.repository.impl;

import fr.arolla.modec.entity.ShippingService;
import fr.arolla.modec.repository.ShippingServiceRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepositoryHibernate extends ShippingServiceRepository, CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
