package fr.arolla.modec.logistic.repository;

import fr.arolla.modec.logistic.entity.Deliverable;
import fr.arolla.modec.logistic.entity.Sku;

public interface DeliverableRepository {
    Deliverable findOneBySku(Sku sku);

    Iterable<Deliverable> findAll();

    void deleteAll();

    Deliverable save(Deliverable product);
}
