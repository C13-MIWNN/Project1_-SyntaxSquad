package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.*;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.AmountOfIngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
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

    private final RecipeUserService recipeUserService;

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository, AmountOfIngredientRepository amountOfIngredientRepository, RecipeUserService recipeUserService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
        this.amountOfIngredientRepository = amountOfIngredientRepository;
        this.recipeUserService = recipeUserService;
    }

    @EventListener
    private void seed(ContextRefreshedEvent event) {
        if (recipeUserService.isNotInitialised()) {
            initializeDB();
        }
    }

    @GetMapping("/initialize")
    private String initializeDB() {
        makeUser("HSS", "HSS");

        Ingredient potatoes = makeIngredient("Potatoes", UnitOfMeasurement.piece);
        Ingredient curry = makeIngredient("Curry", UnitOfMeasurement.gram);
        Ingredient sausages = makeIngredient("Sausages", UnitOfMeasurement.gram);
        Ingredient onions = makeIngredient("Onions", UnitOfMeasurement.piece);
        Ingredient carrots = makeIngredient("Carrots", UnitOfMeasurement.piece);
        Ingredient chickenBreast = makeIngredient("Chicken Breast", UnitOfMeasurement.gram);
        Ingredient rice = makeIngredient("Rice", UnitOfMeasurement.gram);
        Ingredient pasta = makeIngredient("Pasta", UnitOfMeasurement.gram);
        Ingredient tomatoes = makeIngredient("Tomatoes", UnitOfMeasurement.piece);
        Ingredient spinach = makeIngredient("Spinach", UnitOfMeasurement.gram);
        Ingredient broccoli = makeIngredient("Broccoli", UnitOfMeasurement.gram);
        Ingredient bellPepper = makeIngredient("Bell Pepper", UnitOfMeasurement.piece);
        Ingredient garlicCloves = makeIngredient("Garlic Cloves", UnitOfMeasurement.piece);
        Ingredient oliveOil = makeIngredient("Olive Oil", UnitOfMeasurement.tablespoon);

        Tag japaneseKitchen = makeTag("Japanese Kitchen");
        Tag italianKitchen = makeTag("Italian Kitchen");
        Tag mexicanKitchen = makeTag("Mexican Kitchen");
        Tag frenchKitchen = makeTag("French Kitchen");
        Tag chineseKitchen = makeTag("Chinese Kitchen");
        Tag breakfast = makeTag("Breakfast");
        Tag lunch = makeTag("Lunch");
        Tag dinner = makeTag("Dinner");


        AmountOfIngredient gramsSausages = makeAmountOfIngredient(3.00, sausages);
        AmountOfIngredient gramsCurry = makeAmountOfIngredient(2.00, curry);
        AmountOfIngredient pieceOfPotato = makeAmountOfIngredient(1.00, potatoes);
        AmountOfIngredient pieceOfOnion = makeAmountOfIngredient(0.50, onions);
        AmountOfIngredient gramsBroccoli = makeAmountOfIngredient(200.00, broccoli);
        AmountOfIngredient gramsSpinach = makeAmountOfIngredient(100.00, spinach);
        AmountOfIngredient pieceOfTomatoes = makeAmountOfIngredient(1.20, tomatoes);
        AmountOfIngredient pieceOfBellPepper = makeAmountOfIngredient(1.10, bellPepper);
        AmountOfIngredient gramsPasta = makeAmountOfIngredient(150.00, pasta);
        AmountOfIngredient gramsRice = makeAmountOfIngredient(120.00, rice);
        AmountOfIngredient pieceOfGarlicClove = makeAmountOfIngredient(1.10, garlicCloves);
        AmountOfIngredient tablespoonOliveOil = makeAmountOfIngredient(1.60, oliveOil);
        AmountOfIngredient gramsChickenBreast = makeAmountOfIngredient(180.00, chickenBreast);
        AmountOfIngredient pieceOfCarrot = makeAmountOfIngredient(0.60, carrots);


        Recipe curryPotatoes = makeRecipe("Curry Potatoes", "Combine all ingredients",
                Set.of(gramsCurry, gramsSausages, pieceOfPotato, pieceOfOnion), Set.of(japaneseKitchen));

       /* Recipe chickenAndPotatoStew = makeRecipe("Chicken and Potato Stew", "Stew chicken and potatoes together",
                Set.of(gramsChickenBreast, pieceOfPotato, pieceOfOnion), Set.of(italianKitchen));

        Recipe italianSausagePizza = makeRecipe("Italian Sausage Pizza", "Top pizza with Italian sausage slices",
                Set.of(gramsSausages), Set.of(italianKitchen));

        Recipe spicyCurrySausage = makeRecipe("Spicy Curry Sausage", "Cook sausages in a spicy curry sauce",
                Set.of(gramsPasta, gramsSausages), Set.of(mexicanKitchen));*/

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
        AmountOfIngredient existingAmount = amountOfIngredientRepository.findByIngredient(ingredient);

        if (existingAmount != null) {
            existingAmount.setAmount(amount);
            return amountOfIngredientRepository.save(existingAmount);
        } else {
            AmountOfIngredient amountOfIngredient = new AmountOfIngredient();
            amountOfIngredient.setAmount(amount);
            amountOfIngredient.setIngredient(ingredient);
            return amountOfIngredientRepository.save(amountOfIngredient);
        }
    }
        /* AmountOfIngredient amountOfIngredient = new AmountOfIngredient();
         amountOfIngredient.setAmount(amount);
         amountOfIngredient.setIngredient(ingredient);
         amountOfIngredientRepository.save(amountOfIngredient);
         return amountOfIngredient;
     }
*/

     private Recipe makeRecipe(String recipeName, String instructions,
                               Set<AmountOfIngredient> amountOfIngredients, Set<Tag> tags) {
         Optional<Recipe> existingRecipe = recipeRepository.findByRecipeName(recipeName);
         Recipe recipe = null;
         if (existingRecipe.isPresent()) {
             recipe = existingRecipe.get();
             recipe.setInstructions(instructions);
             recipe.setAmountOfIngredients(amountOfIngredients);
             recipe.setTags(tags);
         } else {
             recipe = new Recipe();
             recipe.setRecipeName(recipeName);
             recipe.setInstructions(instructions);
             recipe.setAmountOfIngredients(amountOfIngredients);
             recipe.setTags(tags);
         }
         return recipeRepository.save(recipe);
     }




     private RecipeUser makeUser(String username, String password) {
         RecipeUser user = new RecipeUser();
         user.setUsername(username);
         user.setPassword(password);
         recipeUserService.saveUser(user);
         return user;
     }

}
