package mk.ukim.finki.wp_aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //it initializes at the start of the spring app
public class DataHolder {

    public static List<Category> categoryList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();


    @PostConstruct // initializes and runs at the start of the spring app
    public void init() {
        categoryList.add(new Category("Software", "dasasfdaf"));
        categoryList.add(new Category("Hardware", "dasasffsfaf"));
        categoryList.add(new Category("Software", "dasasfdadadasdadf"));

        userList.add(new User("stefantagarski", "1234", "Stefan", "Tagarski"));

    }

}
