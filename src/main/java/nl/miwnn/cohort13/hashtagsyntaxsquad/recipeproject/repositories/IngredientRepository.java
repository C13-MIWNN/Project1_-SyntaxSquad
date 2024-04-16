package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByName(String name);
    List<Ingredient> findByNameContaining(String partOfName);
}
