package fr.arolla.modec.logistic.domain;

public interface Deliverables {
    Deliverable findOneBySku(Sku sku);

    Iterable<Deliverable> findAll();

    void deleteAll();

    Deliverable save(Deliverable product);
}
