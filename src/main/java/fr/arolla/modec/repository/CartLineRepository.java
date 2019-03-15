package fr.arolla.modec.repository;

import fr.arolla.modec.entity.CartLine;

public interface CartLineRepository {
    CartLine save(CartLine cartLine);
}
