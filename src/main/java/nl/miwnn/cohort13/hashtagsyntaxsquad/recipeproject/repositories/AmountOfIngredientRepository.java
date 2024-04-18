package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.AmountOfIngredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountOfIngredientRepository extends JpaRepository<AmountOfIngredient, Long> {
    AmountOfIngredient findByIngredient(Ingredient ingredient);
}
