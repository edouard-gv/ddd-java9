package fr.arolla.modec.entity;

import fr.arolla.modec.repository.CartRepository;

import java.util.Optional;

public class MockCartRepository implements CartRepository {

    private Cart hardCodedCart;

    @Override
    public Optional<Cart> findById(CartId cartId) {
        return Optional.ofNullable(hardCodedCart);
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    public void setHardCodedCart(Cart cart) {
        this.hardCodedCart = cart;
    }
}
