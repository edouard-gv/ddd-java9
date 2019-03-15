package fr.arolla.modec.repository.impl;

import fr.arolla.modec.entity.Cart;
import fr.arolla.modec.entity.CartId;
import fr.arolla.modec.repository.CartRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositoryHibernate extends CartRepository, CrudRepository<Cart, CartId> {
}
