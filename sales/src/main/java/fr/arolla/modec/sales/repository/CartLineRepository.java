package fr.arolla.modec.sales.repository;

import fr.arolla.modec.sales.entity.CartLine;

public interface CartLineRepository {
    CartLine save(CartLine cartLine);
}
