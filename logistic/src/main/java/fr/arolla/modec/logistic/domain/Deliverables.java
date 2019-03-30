package fr.arolla.modec.logistic.domain;

public interface Deliverables {
    Deliverable findOneBySku(Sku sku);

    void deleteAll();

    Deliverable save(Deliverable deliverable);
}
