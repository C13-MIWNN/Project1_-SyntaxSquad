package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;
/*
 * @author Henk-Jan Veld
 * Set some intial data in the database for testing purposes
 **/
@SuppressWarnings("SameReturnValue")
@Controller
public class InitializeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final TagRepository tagRepository;

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
    }
    @GetMapping("/initialize")
    private String initializeDB() {

        Recipe cheesecake = makeRecipe("Cheesecake");
        Recipe baklava = makeRecipe("Baklava");
        Recipe curryPotatoes = makeRecipe("Curry Potatoes");

        Ingredient cheese = makeIngredient("Cheese");
        Ingredient cake = makeIngredient("Cake");
        Ingredient phylloDough = makeIngredient("Phyllo Dough");
        Ingredient sugar = makeIngredient("Sugar");
        Ingredient potatoes = makeIngredient("Potatoes");
        Ingredient curry = makeIngredient("Curry");

        Tag soep = makeTag("Soep");
        Tag taart = makeTag("Taart");

        return "redirect:/";
    }
    private Ingredient makeIngredient(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private Recipe makeRecipe(String recipeName, String instructions, Set<Ingredient> ingredients, Set<Tag> tags) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setInstructions(instructions);
        recipe.setIngredients(ingredients);
        recipe.setTags(tags);

        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(new Ingredient());
        recipe.setIngredients(ingredientSet);

        Set<Tag> tagSet = new Set<);>
        tagSet.add(new Tag());
        recipe.setTags((tagSet));

        recipeRepository.save(recipe);
        return recipe;
    }
//tag needs to be revised, based on the type of set it will become
    private Tag makeTag(String ) {
        Tag tag = new Tag();
        tag.setTag();
        tagRepository.save(tag);
        return tag;
    }
    /*private Ingredient makeIngredient(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientRepository.save(ingredient);
        return ingredient;
}*/
}
