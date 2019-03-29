package fr.arolla.modec.logistic.service;

import fr.arolla.modec.logistic.entity.Deliverable;
import fr.arolla.modec.logistic.entity.Delivery;
import fr.arolla.modec.logistic.entity.ShippingService;
import fr.arolla.modec.logistic.entity.Sku;
import fr.arolla.modec.logistic.repository.DeliverableRepository;
import fr.arolla.modec.logistic.repository.ShippingServiceRepository;

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
