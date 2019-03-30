package fr.arolla.modec.logistic.infrastructure;

import fr.arolla.modec.logistic.domain.ShippingService;
import fr.arolla.modec.logistic.domain.ShippingServiceRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingServiceRepositoryHibernate extends ShippingServiceRepository, CrudRepository<ShippingService, Long> {
    ShippingService findOneByCode(String code);
}
