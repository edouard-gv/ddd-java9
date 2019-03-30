package fr.arolla.modec.logistic.infrastructure;

import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Sku;
import fr.arolla.modec.logistic.domain.Deliverables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverablesHibernate extends Deliverables, CrudRepository<Deliverable, Long> {
    Deliverable findOneBySku(Sku sku);
}
