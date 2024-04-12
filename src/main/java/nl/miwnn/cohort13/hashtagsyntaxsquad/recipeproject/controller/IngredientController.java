package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author #SyntaxSquad
 * Deal with everything related to ingredients
 **/
@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    List<UnitOfMeasurement> unitsOfMeasurement;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;

    }

    @GetMapping("/ingredient")
    private String showAllIngredients(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));
        model.addAttribute("newIngredient", new Ingredient());

        return "ingredientOverview";
    }

    @PostMapping("/ingredient/new")
    private String saveOrUpdateIngredient
            (@ModelAttribute("newIngredient") Ingredient ingredientToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            ingredientRepository.save(ingredientToBeSaved);
        }

        return "redirect:/ingredient";
    }
}
