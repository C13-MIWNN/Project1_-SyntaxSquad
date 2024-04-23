package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByName(String name);
    List<Ingredient> findByNameContaining(String partOfName);

    Optional<Ingredient> findByNameAndUnitOfMeasurement(String name, UnitOfMeasurement unitOfMeasurement);
}
