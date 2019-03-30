package fr.arolla.modec.logistic.domain.service;

import fr.arolla.modec.logistic.domain.Deliverable;
import fr.arolla.modec.logistic.domain.Delivery;
import fr.arolla.modec.logistic.domain.ShippingService;
import fr.arolla.modec.logistic.domain.Sku;
import fr.arolla.modec.logistic.domain.DeliverableRepository;
import fr.arolla.modec.logistic.domain.ShippingServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {

    private final ShippingServiceRepository shippingServiceRepository;
    private final DeliverableRepository deliverableRepository;

    public DeliveryService(ShippingServiceRepository shippingServiceRepository, DeliverableRepository deliverableRepository) {
        this.shippingServiceRepository = shippingServiceRepository;
        this.deliverableRepository = deliverableRepository;
    }

    public List<ShippingService> getShippingServices(Delivery delivery) {
        List<ShippingService> servicesFound = new ArrayList<>();
        if (delivery.getAddress() != null && !delivery.getLines().isEmpty()) {
            Sku firstDeliverableSku = delivery.getLines().get(0).getDeliverableSku();
            Deliverable firstDeliverable = deliverableRepository.findOneBySku(firstDeliverableSku);
            if (firstDeliverable.getWeight().getWeight() >= 1) {
                servicesFound.add(shippingServiceRepository.findOneByCode("Chrono10"));
            } else {
                servicesFound.add(shippingServiceRepository.findOneByCode("laposte"));
            }
        }
        return servicesFound;
    }
}
