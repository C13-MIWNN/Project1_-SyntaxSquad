package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.IngredientInRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientsTest {

    @Test
    @DisplayName("Recipe with no ingredients")
    void recipeWithoutIngredients() {
        // Arrange
        Recipe recipe = new Recipe();

        // Act
        int actualNoIngredients = recipe.getNumberOfIngredients();

        // Assert
        assertEquals(0, actualNoIngredients);
    }

    @Test
    @DisplayName("Recipe with 3 ingredients")
    void recipeWithLessThanFourIngredients() {
        // Arrange
        Recipe recipe = new Recipe();
        List<IngredientInRecipe> ingredients = new ArrayList<>();
        Ingredient potatoes = makeIngredient("Potatoes", UnitOfMeasurement.PIECE);

        ingredients.add(new IngredientInRecipe(100, potatoes));
        ingredients.add(new IngredientInRecipe(200, makeIngredient("Carrots", UnitOfMeasurement.PIECE)));
        ingredients.add(new IngredientInRecipe(300, makeIngredient("Onions", UnitOfMeasurement.PIECE)));
        recipe.setIngredientInRecipeList(ingredients);

        // Act
        int actualNoIngredients = recipe.getNumberOfIngredients();

        // Assert
        assertEquals(3, actualNoIngredients);
    }

    @Test
    @DisplayName("Recipe with 6 ingredients")
    void recipeWithMoreThanFiveIngredients() {
        // Arrange
        Recipe recipe = new Recipe();
        List<IngredientInRecipe> ingredients = new ArrayList<>();

        ingredients.add(getIngredient(100, makeIngredient("rice", UnitOfMeasurement.GRAM)));
        ingredients.add(getIngredient(200, makeIngredient("chicken", UnitOfMeasurement.GRAM)));
        ingredients.add(getIngredient(300, makeIngredient("broccoli", UnitOfMeasurement.GRAM)));
        ingredients.add(getIngredient(400, makeIngredient("soy sauce", UnitOfMeasurement.MILLILITER)));
        ingredients.add(getIngredient(500, makeIngredient("garlic", UnitOfMeasurement.PIECE)));
        ingredients.add(getIngredient(600, makeIngredient("sesame oil", UnitOfMeasurement.MILLILITER)));

        recipe.setIngredientInRecipeList(ingredients);

        // Act
        int actualNoIngredients = recipe.getNumberOfIngredients();

        // Assert
        assertEquals(6, actualNoIngredients);
    }

    private Ingredient makeIngredient(String name, UnitOfMeasurement unitOfMeasurement) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUnitOfMeasurement(unitOfMeasurement);
        return ingredient;
    }

    private IngredientInRecipe getIngredient(double amount, Ingredient ingredient) {
        IngredientInRecipe ingredientInRecipe = new IngredientInRecipe();
        ingredientInRecipe.setAmount(amount);
        ingredientInRecipe.setIngredient(ingredient);
        return ingredientInRecipe;
    }
}
