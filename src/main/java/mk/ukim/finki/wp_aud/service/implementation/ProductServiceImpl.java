package mk.ukim.finki.wp_aud.service.implementation;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.wp_aud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ProductRepository;
import mk.ukim.finki.wp_aud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturerID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException(categoryID));

        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerID)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerID));

        return Optional.of(productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public Optional<Product> update(Long id, String name, Double price, Integer quantity, Long categoryID, Long manufacturerID) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException(categoryID));

        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerID)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerID));


        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setManufacturer(manufacturer);

        return Optional.of(productRepository.save(product));
    }


    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
