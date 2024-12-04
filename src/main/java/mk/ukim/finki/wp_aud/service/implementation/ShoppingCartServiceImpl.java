package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp_aud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wp_aud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ShoppingCartNotFound;
import mk.ukim.finki.wp_aud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryShoppingCartRepository;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.ProductService;
import mk.ukim.finki.wp_aud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductService productService
            , UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartID) {
        if (shoppingCartRepository.findById(cartID).isEmpty()) {
            throw new ShoppingCartNotFound(cartID);
        }
        return shoppingCartRepository.findById(cartID).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return shoppingCartRepository
                .findByUserAndShoppingCartStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addProductShoppingCart(String username, Long productID) {
        ShoppingCart shoppingCart = getActiveShoppingCart(username);
        Product product = productService.findById(productID).orElseThrow(() -> new ProductNotFoundException(productID));

        if (!shoppingCart.getProducts()
                .stream().filter(r -> r.getId().equals(productID))
                .collect(Collectors.toList()).isEmpty()) {
            throw new ProductAlreadyInShoppingCartException(productID, username);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
