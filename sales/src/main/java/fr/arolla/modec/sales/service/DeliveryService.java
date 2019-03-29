package fr.arolla.modec.sales.service;

import fr.arolla.modec.sales.entity.Order;
import fr.arolla.modec.sales.entity.Product;
import fr.arolla.modec.sales.entity.ShippingService;
import fr.arolla.modec.sales.entity.Sku;
import fr.arolla.modec.sales.repository.ProductRepository;
import fr.arolla.modec.sales.repository.ShippingServiceRepository;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {

    private final ShippingServiceRepository shippingServiceRepository;
    private final ProductRepository productRepository;

    public DeliveryService(ShippingServiceRepository shippingServiceRepository, ProductRepository productRepository) {
        this.shippingServiceRepository = shippingServiceRepository;
        this.productRepository = productRepository;
    }

    public List<ShippingService> getShippingServices(Order order) {
        List<ShippingService> servicesFound = new ArrayList<>();
        if (order.getShippingAddress() != null && !order.getLines().isEmpty()) {
            Sku firstProductSku = order.getLines().get(0).getProductSku();
            Product firstProduct = productRepository.findOneBySku(firstProductSku);
            if (firstProduct.getWeight().getWeight() >= 1) {
                servicesFound.add(shippingServiceRepository.findOneByCode("Chrono10"));
            } else {
                servicesFound.add(shippingServiceRepository.findOneByCode("laposte"));
            }
        }
        return servicesFound;
    }
}
