package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
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

        Ingredient potatoes = makeIngredient("Potatoes", UnitOfMeasurement.GRAM, 88.0);
        Ingredient curry = makeIngredient("Curry", UnitOfMeasurement.TABLESPOON, 30.0);
        Ingredient sausages = makeIngredient("Sausages", UnitOfMeasurement.GRAM, 200.0);
        Ingredient onions = makeIngredient("Onions", UnitOfMeasurement.GRAM, 20.0);
        Ingredient carrots = makeIngredient("Carrots", UnitOfMeasurement.GRAM, 10.0);
        Ingredient chickenBreast = makeIngredient("Chicken Breast", UnitOfMeasurement.GRAM,120.0);
        Ingredient rice = makeIngredient("Rice", UnitOfMeasurement.GRAM, 210.0);
        Ingredient pasta = makeIngredient("Pasta", UnitOfMeasurement.GRAM,250.0);
        Ingredient tomatoes = makeIngredient("Tomatoes", UnitOfMeasurement.GRAM, 15.0);
        Ingredient spinach = makeIngredient("Spinach", UnitOfMeasurement.GRAM, 12.0);
        Ingredient broccoli = makeIngredient("Broccoli", UnitOfMeasurement.GRAM, 10.0);
        Ingredient bellPepper = makeIngredient("Bell Pepper", UnitOfMeasurement.GRAM, 13.0);
        Ingredient garlicCloves = makeIngredient("Garlic Cloves", UnitOfMeasurement.TEASPOON, 5.0);
        Ingredient oliveOil = makeIngredient("Olive Oil", UnitOfMeasurement.TABLESPOON, 100.0);

        Tag japaneseKitchen = makeTag("Japanese Kitchen");
        Tag italianKitchen = makeTag("Italian Kitchen");
        Tag mexicanKitchen = makeTag("Mexican Kitchen");
        Tag frenchKitchen = makeTag("French Kitchen");
        Tag chineseKitchen = makeTag("Chinese Kitchen");
        Tag breakfast = makeTag("Breakfast");
        Tag lunch = makeTag("Lunch");
        Tag dinner = makeTag("Dinner");

        Recipe curryPotatoes = makeRecipe("Spicy Potato Curry",
                List.of("Weigh the ingredients", "Cook all ingredients separately"),
                List.of(new IngredientInRecipe(100, sausages),
                        new IngredientInRecipe(100, potatoes),
                        new IngredientInRecipe(2, curry),
                        new IngredientInRecipe(50, onions)),
                List.of(japaneseKitchen));

        Recipe chickenRiceStirFry = makeRecipe("Rice and Chicken Stirfry",
                List.of("Stirfry"),
                List.of(new IngredientInRecipe(200, rice),
                        new IngredientInRecipe(100, bellPepper),
                        new IngredientInRecipe(100, chickenBreast)),
                List.of(chineseKitchen));

        Recipe chickenAndPotatoStew = makeRecipe("Chicken and broccoli Stew",
                List.of("Stew chicken and potatoes together"),
                List.of(new IngredientInRecipe(100, chickenBreast),
                        new IngredientInRecipe(100, broccoli),
                        new IngredientInRecipe(50, onions)),
                List.of(italianKitchen));

        Recipe italianSausagePizza = makeRecipe("Italian Sausage Pizza",
                List.of("Top pizza with Italian sausage slices"),
                List.of(new IngredientInRecipe(100, sausages)),
                List.of(italianKitchen));

        Recipe spicyCurrySausage = makeRecipe("Spicy Pasta",
                List.of("Cook sausages", "Make a spicy sauce"),
                List.of(new IngredientInRecipe(1, garlicCloves),
                        new IngredientInRecipe(100, tomatoes),
                        new IngredientInRecipe(150, sausages),
                        new IngredientInRecipe(125, pasta)),
                List.of(italianKitchen));

        return "redirect:/";
    }

    private Ingredient makeIngredient(String name, UnitOfMeasurement unitOfMeasurement, Double kCal) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setUnitOfMeasurement(unitOfMeasurement);
        ingredient.setkCal(kCal);

        ingredientRepository.save(ingredient);
        return ingredient;
    }

     private Tag makeTag(String tagName) {
         Tag tag = new Tag();
         tag.setTagName(tagName);

         tagRepository.save(tag);
         return tag;
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

     private RecipeUser makeUser(String username, String password) {
         RecipeUser user = new RecipeUser();
         user.setUsername(username);
         user.setPassword(password);

         recipeUserService.saveUser(user);
         return user;
     }

}
