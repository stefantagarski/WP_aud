package mk.ukim.finki.wp_aud.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFound extends RuntimeException {
    public ShoppingCartNotFound(Long id) {
        super("Shopping cart with ID: " + id + "not found!");
    }
}
