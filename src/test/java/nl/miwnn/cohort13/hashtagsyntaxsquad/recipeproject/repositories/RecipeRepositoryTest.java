package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class RecipeRepositoryTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeRepositoryTest repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test findByRecipeNameContaining")
    public void testFindByRecipeNameContaining() {
        // Arrange
        String searchPhrase = "Chocolate";
        Recipe chocolateCake = new Recipe("Chocolate Cake", null, null, null);
        when(recipeRepository.findByRecipeNameContaining(anyString())).thenReturn(List.of(chocolateCake));

        // Act
        List<Recipe> foundRecipes = recipeRepository.findByRecipeNameContaining(searchPhrase);

        // Assert
        assertEquals(1, foundRecipes.size());
        assertEquals("Chocolate Cake", foundRecipes.get(0).getRecipeName());
    }


    @Test
    @DisplayName("Test findByRecipeName")
    public void testFindByRecipeName() {
        // Arrange
        Recipe vanillaCake = new Recipe("Vanilla Cake", null, null, null);
        when(recipeRepository.findByRecipeName("Vanilla Cake")).thenReturn(Optional.of(vanillaCake));

        // Act
        Optional<Recipe> foundRecipe = recipeRepository.findByRecipeName("Vanilla Cake");

        // Assert
        assertTrue(foundRecipe.isPresent());
        assertEquals("Vanilla Cake", foundRecipe.get().getRecipeName());
    }

    @Test
    @DisplayName("Test findById")
    public void testFindById() {
        // Arrange
        Recipe strawberryCake = new Recipe("Strawberry Cake", null, null, null);
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(strawberryCake));

        // Act
        Optional<Recipe> foundRecipe = recipeRepository.findById(1L);

        // Assert
        assertTrue(foundRecipe.isPresent());
        assertEquals("Strawberry Cake", foundRecipe.get().getRecipeName());
    }
}
