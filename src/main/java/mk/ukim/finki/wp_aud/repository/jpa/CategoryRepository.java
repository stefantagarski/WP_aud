package mk.ukim.finki.wp_aud.repository.jpa;

import mk.ukim.finki.wp_aud.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameLike(String text); //site isti so imeto no i slicni
    void deleteByName(String name);
}
