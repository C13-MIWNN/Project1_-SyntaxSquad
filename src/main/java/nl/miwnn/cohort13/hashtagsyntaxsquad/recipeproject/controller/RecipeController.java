package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
 **/
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping({"/", "/recipe"})
    private String showAllRecipes(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allIngredients", ingredientRepository.findAll());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveRecipe(@ModelAttribute("recipe") Recipe recipeToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            recipeRepository.save(recipeToBeSaved);
        }
        return "redirect:/";
    }



}
