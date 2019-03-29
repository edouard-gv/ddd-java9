package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.ShippingService;
import fr.arolla.modec.sales.repository.ShippingServiceRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepositoryHibernate extends ShippingServiceRepository, CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
