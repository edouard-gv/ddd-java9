package fr.arolla.modec.repository;

import fr.arolla.modec.entity.CartLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartLineRepositoryHibernate extends CartLineRepository, CrudRepository<CartLine, Long> {
}
