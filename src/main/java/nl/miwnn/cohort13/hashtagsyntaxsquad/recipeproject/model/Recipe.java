package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

/**
 * @author #SyntaxSquad
 * Represents a recipe that has ingredients and instructions to prepare it
 **/
@Entity
public class Recipe {

    private static final int DEFAULT_NUMBER_OF_PORTIONS = 4;

    @Id
    @GeneratedValue
    private Long recipeId;
    private String recipeName;
    private String instructions;
    private int numberOfPortions;

    @ManyToMany
    private Set<Ingredient> ingredients;

    @ManyToMany
    private Set<Tag> tags;

    public Recipe(String recipeName, String instructions, Set<Ingredient> ingredients, Set<Tag> tags) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.numberOfPortions = DEFAULT_NUMBER_OF_PORTIONS;
        this.tags = tags;

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
