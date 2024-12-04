package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "shop_users") // specify the name of the table
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    //lazy loading (data is loaded only when needed) as same as @ManyToMany
    // reverse side of the relationship (mappedBy) in ShoppingCart (property) user
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) //add fetch type to eager loading (data is loaded immediately)
    private List<ShoppingCart> shoppingCarts;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
