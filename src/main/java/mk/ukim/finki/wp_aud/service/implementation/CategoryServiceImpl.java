package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    } //constructor based dependency-injection

    @Override
    public Category create(String name, String description) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name, description);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String name, String description) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category category = new Category(name, description);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void delete(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> search(String text) {
        return categoryRepository.search(text);
    }
}