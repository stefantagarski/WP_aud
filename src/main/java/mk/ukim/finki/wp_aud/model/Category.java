package mk.ukim.finki.wp_aud.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //getters and setters
//@AllArgsConstructor //defines constructor
public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }
}
