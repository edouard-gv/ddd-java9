package fr.arolla.modec.repository;

import fr.arolla.modec.entity.OrderLine;

public interface OrderLineRepository {
    OrderLine save(OrderLine orderLine);
}
