package mk.ukim.finki.wp_aud.repository.impl;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findByID(Long id) {
        return DataHolder.shoppingCarts.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        DataHolder.shoppingCarts
                .removeIf(r -> r.getUser().getUsername()
                        .equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status) {
        return DataHolder.shoppingCarts.stream().filter(r -> r.getUser().getUsername()
                .equals(username) && r.getShoppingCartStatus().equals(status)).findFirst();
    }

}
