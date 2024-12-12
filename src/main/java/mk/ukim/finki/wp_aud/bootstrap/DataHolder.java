package mk.ukim.finki.wp_aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //it initializes at the start of the spring app
public class DataHolder {

    public static List<Category> categoryList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Manufacturer> manufacturerList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();


   @PostConstruct // initializes and runs at the start of the spring app
    public void init() {
//        categoryList.add(new Category("Software", "dasasfdaf"));
//        categoryList.add(new Category("Hardware", "dasasffsfaf"));
//        categoryList.add(new Category("Mobiles", "dasasfdadadasdadf"));
//
        userList.add(new User("stefantagarski", "st", "Stefan", "Tagarski"));
//
//
//        manufacturerList.add(new Manufacturer("Apple", "USA"));
//        manufacturerList.add(new Manufacturer("Samsung", "South Korea"));
//        manufacturerList.add(new Manufacturer("Huawei", "China"));
//
//        Category category = categoryList.get(2);
//        Manufacturer manufacturer = manufacturerList.getFirst();
//        productList.add(new Product("iPhone 12", 1200.0, 10, category, manufacturer));
//        productList.add(new Product("iPhone 11", 1000.0, 10, category, manufacturer));
//        productList.add(new Product("iPhone 10", 800.0, 10, category, manufacturer));
//
   }

}
