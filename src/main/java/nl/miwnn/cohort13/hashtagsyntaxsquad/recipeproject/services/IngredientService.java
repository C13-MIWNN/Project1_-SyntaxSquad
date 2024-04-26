package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services;

/*
 * @author Henk-Jan Veld
 * Purpose for this class
 **/

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findIngredientByName(String name) {
        return ingredientRepository.findByNameContaining(name);
    }

    public void saveAllIngredients(List<Ingredient> ingredients) {
        ingredientRepository.saveAll(ingredients);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
