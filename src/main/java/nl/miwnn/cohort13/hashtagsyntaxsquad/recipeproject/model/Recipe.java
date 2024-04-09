package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
 **/
@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long recipeId;
    private String recipeName;
    private String instructions;
    private int numberOfPortions;


    @ManyToMany
    private Set<Ingredient> ingredients;

    public Recipe(String recipeName, String instructions, int numberOfPortions, Set<Ingredient> ingredients) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.numberOfPortions = numberOfPortions;
        this.ingredients = ingredients;

    }

    public Recipe() {

    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(int numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
