package fr.arolla.modec.logistic.domain;

import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Sku;

public interface DeliverableRepository {
    Deliverable findOneBySku(Sku sku);

    Iterable<Deliverable> findAll();

    void deleteAll();

    Deliverable save(Deliverable product);
}
