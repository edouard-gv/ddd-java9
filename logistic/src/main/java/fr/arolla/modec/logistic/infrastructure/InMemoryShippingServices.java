package fr.arolla.modec.logistic.infrastructure;

import fr.arolla.modec.logistic.domain.ShippingService;
import fr.arolla.modec.logistic.domain.ShippingServices;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryShippingServices implements ShippingServices {
    private Map<String, ShippingService> services;

    public InMemoryShippingServices() {
        services = new HashMap<>();
    }

    @Override
    public ShippingService findOneByCode(String code) {
        return services.get(code);
    }

    @Override
    public void deleteAll() {
        services = new HashMap<>();
    }

    @Override
    public ShippingService save(ShippingService shippingService) {
        services.put(shippingService.getCode(), shippingService);
        return shippingService;
    }
}
