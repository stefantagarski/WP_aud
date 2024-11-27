package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    @Enumerated(value = EnumType.STRING) //da se prikazat site enumeracii
    private ShoppingCartStatus shoppingCartStatus;

    public ShoppingCart() {
        this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(User user) {
        this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.shoppingCartStatus = ShoppingCartStatus.CREATED;
    }
}
