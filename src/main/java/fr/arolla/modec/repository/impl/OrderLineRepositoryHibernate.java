package fr.arolla.modec.repository.impl;

import fr.arolla.modec.entity.OrderLine;
import fr.arolla.modec.repository.OrderLineRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepositoryHibernate extends OrderLineRepository, CrudRepository<OrderLine, Long> {
}
