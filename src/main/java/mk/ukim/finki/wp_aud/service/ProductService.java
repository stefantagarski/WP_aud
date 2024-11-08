package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.Product;

import java.util.*;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, Double price,
                           Integer quantity, Long categoryID, Long manufacturerID);

    void deleteById(Long id);

}
