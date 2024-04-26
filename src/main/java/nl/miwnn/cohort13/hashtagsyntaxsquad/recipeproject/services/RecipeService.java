package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services;/*
 * @author Henk-Jan Veld
 * Purpose for this class
 **/

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findRecipesByIngredientName(String ingredient) {
        return recipeRepository.findByRecipeNameContaining(ingredient);
    }
}
