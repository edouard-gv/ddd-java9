package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.OrderLine;
import fr.arolla.modec.sales.repository.OrderLineRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepositoryHibernate extends OrderLineRepository, CrudRepository<OrderLine, Long> {
}
