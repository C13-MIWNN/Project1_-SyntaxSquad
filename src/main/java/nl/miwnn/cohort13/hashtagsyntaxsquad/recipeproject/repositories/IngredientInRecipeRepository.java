package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.IngredientInRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInRecipeRepository extends JpaRepository<IngredientInRecipe, Long> {
    IngredientInRecipe findByIngredient(Ingredient ingredient);
}
