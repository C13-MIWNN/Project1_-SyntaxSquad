package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import jakarta.validation.Valid;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeService;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * @author #SyntaxSquad
 * Deal with everything related to ingredients
 **/
@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final RecipeService recipeService;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository, RecipeService recipeService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/ingredient")
    public String showAllIngredients(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));
        model.addAttribute("newIngredient", new Ingredient());

        return "ingredientOverview";
    }

    @PostMapping("/ingredient")
    public String saveOrUpdateIngredient
            (@ModelAttribute("newIngredient")
             @Valid Ingredient ingredientToBeSaved, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("allIngredients", ingredientRepository.findAll());
            model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));

            return "ingredientOverview";
        }

        ingredientRepository.save(ingredientToBeSaved);

        return "redirect:/ingredient";
    }

    @GetMapping("/search")
    public String searchRecipesByIngredient(@RequestParam("ingredient") String ingredient, Model model) {
        List<Recipe> searchResults = recipeService.findRecipesByIngredientName(ingredient);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
