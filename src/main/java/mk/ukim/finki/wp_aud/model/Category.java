package mk.ukim.finki.wp_aud.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //getters and setters
@AllArgsConstructor //defines constructor
public class Category {
    private String name;
    private String description;
}
