package fr.arolla.modec.repository;

import fr.arolla.modec.entity.CartLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartLineRepository extends CrudRepository<CartLine, Long> {
}
