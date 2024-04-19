package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.*;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientInRecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author #SyntaxSquad
 * Deal with everything related to recipes
 **/
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientInRecipeRepository ingredientInRecipeRepository;
    private final TagRepository tagRepository;

@Autowired
    public RecipeController(RecipeRepository recipeRepository,
                            IngredientRepository ingredientRepository,
                            IngredientInRecipeRepository ingredientInRecipeRepository,
                            TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientInRecipeRepository = ingredientInRecipeRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping({"/", "/index"})
    private String showMainPage(Model model) {

        return "index";
    }

    @GetMapping({"/recipe"})
    private String showAllRecipes(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/recipe/new")
    private String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());

        return setupRecipeForm(model);
    }

    private String setupRecipeForm(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));
        model.addAttribute("allTags", tagRepository.findAll());

        return "recipeForm";
    }

    @PostMapping("/recipe/new")
    private String saveOrUpdateRecipe(@ModelAttribute("recipe") Recipe recipe,
                                          BindingResult result) {

        if (!result.hasErrors()) {
            recipeRepository.save(recipe);

            for (IngredientInRecipe ingredientInRecipe : recipe.getIngredientInRecipeList()) {
                ingredientInRecipe.setRecipe(recipe);
                ingredientInRecipeRepository.save(ingredientInRecipe);
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/recipe/new", params = {"addInstruction"})
    public String addInstruction(final Recipe recipe, final BindingResult bindingResult, Model model) {
        recipe.getInstructions().add("");

        return setupRecipeForm(model);
    }

    @RequestMapping(value = "/recipe/new", params = {"addIngredient"})
    public String addIngredient(final Recipe recipe, final BindingResult bindingResult, Model model) {
        IngredientInRecipe ingredientInRecipe = new IngredientInRecipe();
        ingredientInRecipe.setRecipe(recipe);
        recipe.getIngredientInRecipeList().add(ingredientInRecipe);

        return setupRecipeForm(model);
    }

    @RequestMapping(value = "/recipe/new", params = {"addTag"})
    public String addRowTag(final Recipe recipe, final BindingResult bindingResult, Model model) {
        Tag tag = new Tag();
        tag.setTagName("");
        recipe.getTags().add(tag);

        return setupRecipeForm(model);
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
