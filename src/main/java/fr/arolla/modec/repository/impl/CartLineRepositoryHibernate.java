package fr.arolla.modec.repository.impl;

import fr.arolla.modec.entity.CartLine;
import fr.arolla.modec.repository.CartLineRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartLineRepositoryHibernate extends CartLineRepository, CrudRepository<CartLine, Long> {
}
