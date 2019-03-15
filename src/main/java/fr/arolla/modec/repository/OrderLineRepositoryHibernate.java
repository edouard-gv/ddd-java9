package fr.arolla.modec.repository;

import fr.arolla.modec.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepositoryHibernate extends OrderLineRepository, CrudRepository<OrderLine, Long> {
}
