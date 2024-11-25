package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartID);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductShoppingCart(String username, Long productID);

}
