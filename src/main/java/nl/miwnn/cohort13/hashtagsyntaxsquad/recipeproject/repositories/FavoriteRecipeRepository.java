package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.FavoriteRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {

    List<FavoriteRecipe> findByFavoriteId(Long favoriteId);


    List<FavoriteRecipe> findByUserId(Long userId);

    boolean existsByUserIdAndId(Long userId, Long id);
}