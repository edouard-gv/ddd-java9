package fr.arolla.modec.logistic.domain.service;

import fr.arolla.modec.logistic.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingServicesCalculator implements ICalculateShippingServices {

    private final ShippingServices shippingServices;
    private final Deliverables deliverables;

    public ShippingServicesCalculator(ShippingServices shippingServices, Deliverables deliverables) {
        this.shippingServices = shippingServices;
        this.deliverables = deliverables;
    }

    public List<ShippingService> calculate(Delivery delivery) {
        List<ShippingService> servicesFound = new ArrayList<>();
        if (delivery.getAddress() != null && !delivery.getLines().isEmpty()) {
            Sku firstDeliverableSku = delivery.getLines().get(0).getDeliverableSku();
            Deliverable firstDeliverable = deliverables.findOneBySku(firstDeliverableSku);
            if (firstDeliverable.getWeight().getWeight() >= 1) {
                servicesFound.add(shippingServices.findOneByCode("Chrono10"));
            } else {
                servicesFound.add(shippingServices.findOneByCode("laposte"));
            }
        }
        return servicesFound;
    }
}
