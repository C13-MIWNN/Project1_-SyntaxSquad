package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.service;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeServiceTest {

    @Test
    @DisplayName("Verify correct number of recipes returned by getAllRecipes method")
    void getAllRecipes_shouldReturnCorrectNumberOfRecipes() {
        // Arrange
        RecipeRepository mockRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(mockRepository);
        List<Recipe> mockedRecipes = Arrays.asList(new Recipe(), new Recipe(), new Recipe());
        Mockito.when(mockRepository.findAll()).thenReturn(mockedRecipes);
        int expectedNumberOfRecipes = 3;

        // Act
        List<Recipe> recipes = recipeService.getAllRecipes();
        int actualNumberOfRecipes = recipes.size();

        // Assert
        assertEquals(expectedNumberOfRecipes, actualNumberOfRecipes, "Number of recipes should match");
    }
}
