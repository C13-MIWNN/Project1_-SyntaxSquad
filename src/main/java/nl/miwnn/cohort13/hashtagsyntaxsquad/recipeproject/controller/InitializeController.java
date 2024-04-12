package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.AmountOfIngredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.AmountOfIngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;
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
    private final AmountOfIngredientRepository amountOfIngredientRepository;

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository, AmountOfIngredientRepository amountOfIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
        this.amountOfIngredientRepository = amountOfIngredientRepository;
    }
    @GetMapping("/initialize")
    private String initializeDB() {



        Ingredient potatoes = makeIngredient("Potatoes", UnitOfMeasurement.piece);
        Ingredient curry = makeIngredient("Curry", UnitOfMeasurement.gram);
        Ingredient sausages = makeIngredient("Sausages", UnitOfMeasurement.gram);
        Ingredient onions = makeIngredient("Onions", UnitOfMeasurement.piece);

        Tag japaneseKitchen = makeTag("Japanese Kitchen");

        AmountOfIngredient gramsSausages = makeAmountOfIngredient(3.00, sausages);
        AmountOfIngredient gramsCurry = makeAmountOfIngredient(2.00, curry);
        AmountOfIngredient pieceOfPotato = makeAmountOfIngredient(1.00, potatoes);
        AmountOfIngredient pieceOfOnion = makeAmountOfIngredient(0.50, onions);

        Recipe curryPotatoes = makeRecipe
                ("Curry Potatoes", "Combine all ingredients",
                        Set.of(gramsCurry, gramsSausages, pieceOfPotato, pieceOfOnion), Set.of(japaneseKitchen));



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
     private AmountOfIngredient makeAmountOfIngredient(Double amount, Ingredient ingredient) {
         AmountOfIngredient amountOfIngredient = new AmountOfIngredient();
         amountOfIngredient.setAmount(amount);
         amountOfIngredient.setIngredient(ingredient);
         amountOfIngredientRepository.save(amountOfIngredient);
         return amountOfIngredient;
     }

    private Recipe makeRecipe(String recipeName, String instructions,
                              Set<AmountOfIngredient> amountOfIngredients, Set<Tag> tags) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeName);
        recipe.setInstructions(instructions);
        recipe.setAmountOfIngredients(amountOfIngredients);
        recipe.setTags(tags);

        recipeRepository.save(recipe);
        return recipe;
    }

}
