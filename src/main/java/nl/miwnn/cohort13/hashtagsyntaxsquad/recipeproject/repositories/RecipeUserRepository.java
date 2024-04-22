package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeUserRepository extends JpaRepository<RecipeUser, Long> {
    Optional<RecipeUser> findByUsername(String username);

    Optional<RecipeUser> findByUserId(Long userId);


}