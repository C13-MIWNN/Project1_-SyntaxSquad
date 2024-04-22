import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.IngredientInRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IngredientsInRecipeTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private IngredientsInRecipeTest ingredientsInRecipeTest;

    @Test
    void testIngredientsInRecipe() {
        Long recipeId = 1L;

        Recipe mockRecipe = new Recipe();

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(mockRecipe));

        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        assertNotNull(recipe, "Recipe not found");

        List<IngredientInRecipe> retrievedIngredients = recipe.getIngredientInRecipeList();

        assertEquals(4, retrievedIngredients.size(), "Number of ingredients does not match");
    }
}
