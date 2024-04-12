package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.AmountOfIngredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.AmountOfIngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Sarah Fopma
 * <p>
 * Programma <doet het volgende...>
 **/
@Controller
public class AmountOfIngredientController {
    private final AmountOfIngredientRepository amountOfIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public AmountOfIngredientController(AmountOfIngredientRepository amountOfIngredientRepository,
                                        IngredientRepository ingredientRepository) {
        this.amountOfIngredientRepository = amountOfIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @GetMapping("/amount")
    private String showAllAmountsOfIngredients(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allAmountsOfIngredients", amountOfIngredientRepository.findAll());
        model.addAttribute("newAmountOfIngredient", new AmountOfIngredient());

        return "amountOfIngredientOverview";
    }

    @PostMapping("/amount/new")
    private String saveOrUpdateAmountOfIngredient
            (@ModelAttribute("newAmountOfIngredient") AmountOfIngredient amountOfIngredient, BindingResult result) {
        if (!result.hasErrors()) {
            amountOfIngredientRepository.save(amountOfIngredient);
        }

        return "redirect:/amount";
    }
}
