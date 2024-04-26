package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import jakarta.validation.Valid;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.exceptions.UniqueIngredientNameAndUnitConstraintException;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.IngredientService;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeService;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Recipe;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author #SyntaxSquad
 * Deal with everything related to ingredients
 **/
@Controller
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository,
                                RecipeService recipeService,
                                IngredientService ingredientService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient")
    public String showAllIngredient(Model model) {
        model.addAttribute("newIngredient", new Ingredient());

        return setupIngredientOverview(model);
    }

    @PostMapping("/ingredient")
    public String saveOrUpdateIngredient
            (@ModelAttribute("newIngredient")
             @Valid Ingredient ingredientToBeSaved, BindingResult result, Model model)
            throws UniqueIngredientNameAndUnitConstraintException {

        String checkForNameAndUnitConstraint =
                handleIngredientNameAndUnitConstraintIfNecessary(ingredientToBeSaved, model);
        if (checkForNameAndUnitConstraint != null) return checkForNameAndUnitConstraint;

        if (result.hasErrors()) {
            return setupIngredientOverview(model);
        }
        ingredientRepository.save(ingredientToBeSaved);

        return "redirect:/ingredient";
    }

    @GetMapping("/search")
    public String searchRecipesByIngredient(@RequestParam("ingredient") String ingredient, Model model) {
        List<Recipe> searchResults = recipeService.findRecipesByIngredientName(ingredient);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }


    @GetMapping("/ingredientDB")
    public String showAllIngredients(Model model) {
        model.addAttribute("newIngredient", new Ingredient());
        readAndSaveIngredientsFromCSV("C:\\Users\\Henk-Jan\\IdeaProjects\\MIWMNN\\Project1_-SyntaxSquad\\target\\classes\\static\\files\\NEVO2023_v8.0_details.csv");
        return setupIngredientOverview(model);
    }

    @GetMapping("/api/ingredients")
    @ResponseBody
    public List<Ingredient> fetchAllIngredients() {
        return ingredientRepository.findAll();
    }

    private static Double extractKcal(String[] parts, BufferedReader br) throws IOException {
        String line;
        if (isValidLine(parts)) {
            return Double.parseDouble(parts[12]);
        } else {
            while ((line = br.readLine()) != null) {
                String[] nextParts = line.split("\\|");
                if (isValidLine(nextParts)) {
                    return Double.parseDouble(nextParts[12]);
                }
            }
        }
        return null;
    }

    private String handleIngredientNameAndUnitConstraintIfNecessary
            (Ingredient ingredientToBeSaved, Model model) {
        try {
            if (!isIngredientUnique(ingredientToBeSaved)) {
                model.addAttribute("errorMessageUniqueIngredientNameAndUnit",
                        "This combination of ingredient and unit of measurement has already been added.");
                throw new UniqueIngredientNameAndUnitConstraintException
                        ("This combination of ingredient and unit of measurement has already been added.");
            }
        } catch (UniqueIngredientNameAndUnitConstraintException error) {
            return setupIngredientOverview(model);
        }
        return null;
    }

    private boolean isIngredientUnique(Ingredient ingredient) {
        return ingredientRepository.findByNameAndUnitOfMeasurement
                (ingredient.getName(), ingredient.getUnitOfMeasurement()).isEmpty();
    }

    private static boolean isValidLine(String[] parts) {
        return parts.length >= 14 && "Energy kcal".equals(parts[11].trim());
    }

    public static List<Ingredient> readIngredientsFromCSV(String csvFile) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (isValidLine(parts)) {
                    Double kCal = extractKcal(parts, br);
                    if (kCal != null) {
                        ingredients.add(new Ingredient(null, parts[5], UnitOfMeasurement.GRAM, kCal));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }
        return ingredients;
    }

    public void readAndSaveIngredientsFromCSV(String csvFilePath) {
        List<Ingredient> ingredients = IngredientController.readIngredientsFromCSV(csvFilePath);

        for (Ingredient ingredient : ingredients) {
            if (!isIngredientUnique(ingredient)) {
                continue;
            }
            ingredientService.saveIngredient(ingredient);
        }
        ingredientService.saveAllIngredients(ingredients);
    }

    private String setupIngredientOverview(Model model) {
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        model.addAttribute("allUnitsOfMeasurement", Arrays.asList(UnitOfMeasurement.values()));
        return "ingredientOverview";
    }
}
