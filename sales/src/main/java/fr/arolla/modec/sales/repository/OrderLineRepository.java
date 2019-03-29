package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.OrderLine;

public interface OrderLineRepository {
    OrderLine save(OrderLine orderLine);
}
