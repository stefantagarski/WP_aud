package mk.ukim.finki.wp_aud.repository;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository { //adapter

    public List<Category> findAll() {
        return DataHolder.categoryList;
    }

    public Category save(Category category) {
        if (category.getName().isEmpty()) {
            return null;
        }
        DataHolder.categoryList.removeIf(r -> r.getName().equals(category.getName()));
        DataHolder.categoryList.add(category);
        return category;
    }

    public Optional<Category> findByName(String name) {
        return DataHolder.categoryList.stream().filter(r -> r.getName().equals(name)).findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categoryList.stream().filter(r -> r.getName().equals(text)
                || r.getDescription().equals(text)).collect(Collectors.toList());
    }

    public Optional<Category> findById(Long id) {
        return DataHolder.categoryList.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public void delete(String text) {
        if (text == null) {
            return;
        }
        DataHolder.categoryList.removeIf(r -> r.getName().equals(text));
    }

}
