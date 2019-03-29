package fr.arolla.modec.sales.repository.impl;

import fr.arolla.modec.sales.entity.Cart;
import fr.arolla.modec.sales.entity.CartId;
import fr.arolla.modec.sales.repository.CartRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositoryHibernate extends CartRepository, CrudRepository<Cart, CartId> {
}
