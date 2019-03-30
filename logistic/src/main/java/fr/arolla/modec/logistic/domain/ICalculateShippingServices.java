package fr.arolla.modec.logistic.domain;

import java.util.List;

public interface ICalculateShippingServices {
    public List<ShippingService> calculate(Delivery delivery);
}
