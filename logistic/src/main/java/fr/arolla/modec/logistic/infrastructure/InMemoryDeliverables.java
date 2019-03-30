package fr.arolla.modec.logistic.infrastructure;

import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Deliverables;
import fr.arolla.modec.logistic.domain.Sku;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryDeliverables implements Deliverables {

    private Map<Sku, Deliverable> deliverables;

    public InMemoryDeliverables() {
        deliverables = new HashMap<>();
    }

    @Override
    public Deliverable findOneBySku(Sku sku) {
        return deliverables.get(sku);
    }

    @Override
    public void deleteAll() {
        deliverables = new HashMap<>();
    }

    @Override
    public Deliverable save(Deliverable deliverable) {
        deliverables.put(deliverable.getSku(), deliverable);
        return deliverable;
    }
}
