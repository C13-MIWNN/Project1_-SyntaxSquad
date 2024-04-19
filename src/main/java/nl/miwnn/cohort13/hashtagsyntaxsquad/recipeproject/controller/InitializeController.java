package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.*;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientInRecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
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
    private final RecipeUserService recipeUserService;

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRepository ingredientRepository,
                                TagRepository tagRepository,
                                IngredientInRecipeRepository ingredientInRecipeRepository,
                                RecipeUserService recipeUserService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
        this.ingredientInRecipeRepository = ingredientInRecipeRepository;
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

        IngredientInRecipe gramsSausages = makeIngredientInRecipe(3.00, sausages);
        IngredientInRecipe gramsCurry = makeIngredientInRecipe(2.00, curry);
        IngredientInRecipe pieceOfPotato = makeIngredientInRecipe(1.00, potatoes);
        IngredientInRecipe pieceOfOnion = makeIngredientInRecipe(0.50, onions);
        IngredientInRecipe gramsBroccoli = makeIngredientInRecipe(200.00, broccoli);
        IngredientInRecipe gramsSpinach = makeIngredientInRecipe(100.00, spinach);
        IngredientInRecipe pieceOfTomatoes = makeIngredientInRecipe(1.20, tomatoes);
        IngredientInRecipe pieceOfBellPepper = makeIngredientInRecipe(1.10, bellPepper);
        IngredientInRecipe gramsPasta = makeIngredientInRecipe(150.00, pasta);
        IngredientInRecipe gramsRice = makeIngredientInRecipe(120.00, rice);
        IngredientInRecipe pieceOfGarlicClove = makeIngredientInRecipe(1.10, garlicCloves);
        IngredientInRecipe tablespoonOliveOil = makeIngredientInRecipe(1.60, oliveOil);
        IngredientInRecipe gramsChickenBreast = makeIngredientInRecipe(180.00, chickenBreast);
        IngredientInRecipe pieceOfCarrot = makeIngredientInRecipe(0.60, carrots);

        Recipe curryPotatoes = makeRecipe("Spicy Curry",
                List.of("Weigh the ingredients", "Cook all ingredients separately"),
                List.of(gramsCurry, pieceOfCarrot, gramsBroccoli),
                List.of(japaneseKitchen));

        Recipe chickenRiceStirFry = makeRecipe("Rice Stirfry",
                List.of("Stirfry"),
                List.of(gramsRice, pieceOfBellPepper),
                List.of(chineseKitchen));

        Recipe chickenAndPotatoStew = makeRecipe("Chicken and Potato Stew",
                List.of("Stew chicken and potatoes together"),
                List.of(gramsChickenBreast, pieceOfPotato, pieceOfOnion),
                List.of(mexicanKitchen));

        Recipe italianSausagePizza = makeRecipe("Italian Sausage Pizza",
                List.of("Top pizza with Italian sausage slices"),
                List.of(gramsSausages),
                List.of(italianKitchen));

        Recipe spicyCurrySausage = makeRecipe("Spicy Pasta",
                List.of("Cook sausages", "Make a spicy sauce"),
                List.of(pieceOfGarlicClove, pieceOfTomatoes, gramsCurry),
                List.of(italianKitchen));


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

         for (IngredientInRecipe ingredientInRecipe : ingredientInRecipeList) {
             ingredientInRecipe.setRecipe(recipe);
             ingredientInRecipeRepository.save(ingredientInRecipe);
         }

         return recipe;
     }

//    private AmountOfIngredient makeAmountOfIngredient(Double amount, Ingredient ingredient) {
//        AmountOfIngredient existingAmount = amountOfIngredientRepository.findByIngredient(ingredient);
//
//        if (existingAmount != null) {
//            existingAmount.setAmount(amount);
//            return amountOfIngredientRepository.save(existingAmount);
//        } else {
//            AmountOfIngredient amountOfIngredient = new AmountOfIngredient();
//            amountOfIngredient.setAmount(amount);
//            amountOfIngredient.setIngredient(ingredient);
//            return amountOfIngredientRepository.save(amountOfIngredient);
//        }
//    }

//     private Recipe makeRecipe(String recipeName, String instructions,
//                               Set<AmountOfIngredient> amountOfIngredients, Set<Tag> tags) {
//         Optional<Recipe> existingRecipe = recipeRepository.findByRecipeName(recipeName);
//         Recipe recipe = null;
//         if (existingRecipe.isPresent()) {
//             recipe = existingRecipe.get();
//         } else {
//             recipe = new Recipe();
//             recipe.setRecipeName(recipeName);
//         }
//         recipe.setInstructions(instructions);
//         recipe.setAmountOfIngredients(amountOfIngredients);
//         recipe.setTags(tags);
//         return recipeRepository.save(recipe);
//     }

     private RecipeUser makeUser(String username, String password) {
         RecipeUser user = new RecipeUser();
         user.setUsername(username);
         user.setPassword(password);

         recipeUserService.saveUser(user);
         return user;
     }

}
