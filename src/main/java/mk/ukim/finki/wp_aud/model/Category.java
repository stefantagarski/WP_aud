package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data //getters and setters
//@AllArgsConstructor //defines constructor
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment, no need to set id
    private Long id;

    private String name;

    @Column(length = 5000)
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
