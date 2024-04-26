package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.FavoriteRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FavoriteRecipeRepositoryTest {

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Autowired
    private RecipeUserRepository recipeUserRepository;

    @Test
    public void testCountByRecipeUser() {
        // Arrange
        RecipeUser recipeUser = new RecipeUser();
        recipeUserRepository.save(recipeUser);

        // Act
        long count = favoriteRecipeRepository.countByRecipeUser(recipeUser);
//        long expectedCount = favoriteRecipeRepository.findByRecipeUser(recipeUser).size();
         long expectedCount = 0;

        // Assert
        assertEquals(expectedCount, count);
    }
}


