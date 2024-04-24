package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import jakarta.validation.Valid;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.exceptions.UniqueIngredientNameAndUnitConstraintException;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeService;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
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
        model.addAttribute("newIngredient", new Ingredient());

        return setupIngredientOverview(model);
    }

    @PostMapping("/ingredient")
    public String saveOrUpdateIngredient
            (@ModelAttribute("newIngredient")
             @Valid Ingredient ingredientToBeSaved, BindingResult result, Model model)
            throws UniqueIngredientNameAndUnitConstraintException {

        String checkForNameAndUnitConstraint =
                handleIngredientNameAndUnitConstraintIfNecessary(ingredientToBeSaved, model);
        if (checkForNameAndUnitConstraint != null) return checkForNameAndUnitConstraint;

        if (result.hasErrors()) {
            return setupIngredientOverview(model);
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

    private String handleIngredientNameAndUnitConstraintIfNecessary
            (Ingredient ingredientToBeSaved, Model model) {
        try {
            if (!isIngredientUnique(ingredientToBeSaved)) {
                model.addAttribute("errorMessageUniqueIngredientNameAndUnit",
                        "This combination of ingredient and unit of measurement has already been added.");
                throw new UniqueIngredientNameAndUnitConstraintException
                        ("This combination of ingredient and unit of measurement has already been added.");
            }
        } catch (UniqueIngredientNameAndUnitConstraintException error) {
            return setupIngredientOverview(model);
        }
        return null;
    }

    private boolean isIngredientUnique(Ingredient ingredient) {
        return ingredientRepository.findByNameAndUnitOfMeasurement
                (ingredient.getName(), ingredient.getUnitOfMeasurement()).isEmpty();
    }

    private String setupIngredientOverview(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));
        return "ingredientOverview";
    }
}
