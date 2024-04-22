package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

/**
 * @author Sarah Fopma
 * Deals with the amount of each ingredient in a recipe
 **/

@Entity
   public class IngredientInRecipe {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Ingredient ingredient;

    private double amount;

    public IngredientInRecipe(double amount, Ingredient ingredient) {
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public IngredientInRecipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %s of %s",
                this.amount, this.ingredient.getUnitOfMeasurement(), this.ingredient.getName()).toLowerCase();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

