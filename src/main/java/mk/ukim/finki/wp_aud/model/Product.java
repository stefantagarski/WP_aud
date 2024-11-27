package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    private Long id; //key

    private String name;

    private Double price;

    private Integer quantity;

    //eager loading (data is loaded immediately) as in @OneToOne as well
    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    public Product(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }
}
