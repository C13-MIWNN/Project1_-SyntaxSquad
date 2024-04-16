package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.AmountOfIngredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.AmountOfIngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author #SyntaxSquad
 * Deal with everything related to recipes
 **/
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final AmountOfIngredientRepository amountOfIngredientRepository;
    private final TagRepository tagRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository,
                            AmountOfIngredientRepository amountOfIngredientRepository,
                            TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.amountOfIngredientRepository = amountOfIngredientRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping({"/", "/index"})
    private String showMainPage(Model model) {

        return "index";
    }
    @GetMapping({ "/recipe"})
    private String showAllRecipes(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        // uiteindelijk moet AmountOfIngredient denk ik op dezelfde pagina aangemaakt worden als recipe.
        // Als tussenoplossing nu een amountOfIngredientOverview gemaakt.
//        AmountOfIngredient amountOfIngredientToAdd = new AmountOfIngredient();
//        model.addAttribute("amountOfIngredient", amountOfIngredientToAdd);
//        model.addAttribute("recipe", new Recipe(amountOfIngredientToAdd));
        model.addAttribute("allAmountsOfIngredients", amountOfIngredientRepository.findAll());
        model.addAttribute("allTags", tagRepository.findAll());

        model.addAttribute("newRecipe", new Recipe());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipeToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            recipeRepository.save(recipeToBeSaved);
        }

        return "redirect:/";
    }

    @GetMapping("/recipe/detail/{recipeName}")
    public String showRecipeDetails(@PathVariable("recipeName") String recipeName, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeName(recipeName);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe";
        }

        Recipe recipe = recipeOptional.get();
        byte[] imageData = recipe.getImageData();

        model.addAttribute("recipeToBeShown", recipe);
        model.addAttribute("recipeImageData", imageData);

        return "recipeDetails";
    }
}
