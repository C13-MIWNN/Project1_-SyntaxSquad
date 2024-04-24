package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.FavoriteRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

    List<FavoriteRecipe> findByFavoriteId(Long favoriteId);


    List<FavoriteRecipe> findByRecipeUser(RecipeUser recipeUser);

    List<FavoriteRecipe> findByRecipe(Recipe recipe);

    boolean existsByRecipeAndRecipeUser(Recipe recipe, RecipeUser recipeUser);

    long countByRecipeUser(RecipeUser recipeUser);
}
