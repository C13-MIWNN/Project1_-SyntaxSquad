package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.IngredientInRecipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientInRecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

 /** @author Henk-Jan Veld
 * Set some intial data in the database for testing purposes
 *
*/
@SuppressWarnings("SameReturnValue")
@Controller
public class InitializeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final TagRepository tagRepository;
    private final IngredientInRecipeRepository ingredientInRecipeRepository;

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository,
                                IngredientInRecipeRepository ingredientInRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
        this.ingredientInRecipeRepository = ingredientInRecipeRepository;
    }
    @GetMapping("/initialize")
    private String initializeDB() {

        Ingredient potatoes = makeIngredient("Potatoes", UnitOfMeasurement.piece);
        Ingredient curry = makeIngredient("Curry", UnitOfMeasurement.gram);
        Ingredient sausages = makeIngredient("Sausages", UnitOfMeasurement.gram);
        Ingredient onions = makeIngredient("Onions", UnitOfMeasurement.piece);

        Tag japaneseKitchen = makeTag("Japanese Kitchen");

        IngredientInRecipe gramsSausages = makeIngredientInRecipe(3.00, sausages);
        IngredientInRecipe gramsCurry = makeIngredientInRecipe(2.00, curry);
        IngredientInRecipe pieceOfPotato = makeIngredientInRecipe(1.00, potatoes);
        IngredientInRecipe pieceOfOnion = makeIngredientInRecipe(0.50, onions);


        Recipe curryPotatoes = makeRecipe
                ("Curry Potatoes", List.of("Weigh the ingredients", "Cook all ingredients separately"),
                        List.of(gramsCurry, gramsSausages, pieceOfPotato, pieceOfOnion), List.of(japaneseKitchen));

        return "redirect:/";
    }
    private Ingredient makeIngredient(String name, UnitOfMeasurement unitOfMeasurement) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUnitOfMeasurement(unitOfMeasurement);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

     private Tag makeTag(String tagName) {
         Tag tag = new Tag();
         tag.setTagName(tagName);
         tagRepository.save(tag);
         return tag;
     }
     private IngredientInRecipe makeIngredientInRecipe(Double amount, Ingredient ingredient) {
         IngredientInRecipe ingredientInRecipe = new IngredientInRecipe();
         ingredientInRecipe.setAmount(amount);
         ingredientInRecipe.setIngredient(ingredient);
         ingredientInRecipeRepository.save(ingredientInRecipe);
         return ingredientInRecipe;
     }

     private Recipe makeRecipe(String recipeName, List<String> instructions,
                              List<IngredientInRecipe> ingredientInRecipeList, List<Tag> tags) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setInstructions(instructions);
        recipe.setIngredientInRecipeList(ingredientInRecipeList);
        recipe.setTags(tags);

        recipeRepository.save(recipe);
        return recipe;
    }

}
