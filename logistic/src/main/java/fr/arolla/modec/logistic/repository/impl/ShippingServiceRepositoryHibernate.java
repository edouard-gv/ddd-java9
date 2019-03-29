package fr.arolla.modec.logistic.repository.impl;

import fr.arolla.modec.logistic.entity.ShippingService;
import fr.arolla.modec.logistic.repository.ShippingServiceRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepositoryHibernate extends ShippingServiceRepository, CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
