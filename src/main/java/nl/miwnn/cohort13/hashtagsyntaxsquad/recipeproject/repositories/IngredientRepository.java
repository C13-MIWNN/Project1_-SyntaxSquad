package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
 **/
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
