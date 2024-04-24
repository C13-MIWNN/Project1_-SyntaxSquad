package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services;/*
 * @author Henk-Jan Veld
 * Purpose for this class
 **/

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public List<Ingredient> findIngredientByName(String name) {
        return ingredientRepository.findByNameContaining(name);
    }

    public List<Ingredient> readIngredientsFromCSV(String csvFile) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (isValidLine(parts)) {
                    Double kCal = extractKcal(parts, br);
                    if (kCal != null) {
                        ingredients.add(new Ingredient(null, parts[2], UnitOfMeasurement.GRAMS, kCal));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }
        return ingredients;
    }

    private boolean isValidLine(String[] parts) {
        return parts.length >= 14 && "Energy kcal".equals(parts[11].trim());
    }

    private Double extractKcal(String[] parts, BufferedReader br) throws IOException {
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

    public void readAndSaveIngredientsFromCSV(String csvFilePath) {
        List<Ingredient> ingredients = ingredientService.readIngredientsFromCSV(csvFilePath);
        ingredientService.saveAllIngredients(ingredients);
    }

    private void saveAllIngredients(List<Ingredient> ingredients) {
    }


}
