package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.FavoriteRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.FavoriteRecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sami ARSLAN
 * <p>
 * Add and view recipes to favorite recipes
 **/

@Controller
public class FavoriteRecipeController {

    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeUserRepository recipeUserRepository;

    @Autowired
    public FavoriteRecipeController(FavoriteRecipeRepository favoriteRecipeRepository, RecipeRepository recipeRepository, RecipeUserRepository recipeUserRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.recipeRepository = recipeRepository;
        this.recipeUserRepository = recipeUserRepository;
    }

    @GetMapping("/favorites/add/{id}")
    public String addToFavorite(@PathVariable("id") Long id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((RecipeUser) authentication.getPrincipal()).getUserId();

        boolean isAlreadyRecipe = favoriteRecipeRepository.existsByUserIdAndId(userId, id);

        if (!isAlreadyRecipe) {
            Optional<Recipe> recipeOptional = recipeRepository.findById(id);
            recipeOptional.ifPresent(recipe -> {
                FavoriteRecipe favoriteRecipe = new FavoriteRecipe(userId, id);
                favoriteRecipeRepository.save(favoriteRecipe);
            });
        }
        return "favoriteRecipe";
    }

    @GetMapping("/favorites")
    public String showFavoriteRecipes(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        RecipeUser user = recipeUserRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return "redirect:/error";
        }

        Long userId = user.getUserId();
        List<FavoriteRecipe> favoriteRecipes = favoriteRecipeRepository.findByUserId(userId);

        List<Recipe> userFavoriteRecipes = new ArrayList<>();

        for (FavoriteRecipe favoriteRecipe : favoriteRecipes) {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(favoriteRecipe.getId());
            optionalRecipe.ifPresent(userFavoriteRecipes::add);
        }
        model.addAttribute("favoriteRecipes", userFavoriteRecipes);

            return "favoriteRecipe";
        }
}

