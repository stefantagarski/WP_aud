package mk.ukim.finki.wp_aud.repository;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll() {
        return DataHolder.productList;
    }

    public Optional<Product> findById(Long id) {
        return DataHolder.productList.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        DataHolder.productList.removeIf(r -> r.getQuantity().equals(quantity));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.productList.add(product);
        return Optional.of(product);
    }

    public Optional<Product> findByName(String name) {
        return DataHolder.productList.stream().filter(r -> r.getName().equals(name)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.productList.removeIf(r -> r.getId().equals(id));
    }

}
