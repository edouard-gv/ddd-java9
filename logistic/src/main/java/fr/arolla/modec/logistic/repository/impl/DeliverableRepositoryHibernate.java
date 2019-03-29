package fr.arolla.modec.logistic.repository.impl;

import fr.arolla.modec.logistic.entity.Deliverable;
import fr.arolla.modec.logistic.entity.Sku;
import fr.arolla.modec.logistic.repository.DeliverableRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverableRepositoryHibernate extends DeliverableRepository, CrudRepository<Deliverable, Long> {
    Deliverable findOneBySku(Sku sku);
}
