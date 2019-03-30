package fr.arolla.modec.logistic.infrastructure;

import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Sku;
import fr.arolla.modec.logistic.domain.DeliverableRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverableRepositoryHibernate extends DeliverableRepository, CrudRepository<Deliverable, Long> {
    Deliverable findOneBySku(Sku sku);
}
