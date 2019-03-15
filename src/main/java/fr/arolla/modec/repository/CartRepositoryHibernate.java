package fr.arolla.modec.repository;

import fr.arolla.modec.entity.Cart;
import fr.arolla.modec.entity.CartId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositoryHibernate extends CartRepository, CrudRepository<Cart, CartId> {
}
