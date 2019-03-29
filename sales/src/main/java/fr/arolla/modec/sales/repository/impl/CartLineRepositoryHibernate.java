package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.CartLine;
import fr.arolla.modec.sales.repository.CartLineRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartLineRepositoryHibernate extends CartLineRepository, CrudRepository<CartLine, Long> {
}
