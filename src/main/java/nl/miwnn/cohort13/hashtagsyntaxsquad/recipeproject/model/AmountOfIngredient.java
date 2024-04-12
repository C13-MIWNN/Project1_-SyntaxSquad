package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

/**
 * @author Sarah Fopma
 * Deals with the amount of each ingredient in a recipe
 **/

@Entity
public class AmountOfIngredient {

    @Id
    @GeneratedValue
    private Long id;

    private double amount;

    @ManyToOne
    private Ingredient ingredient;

//    @ManyToOne
//    private Recipe recipe;

    public AmountOfIngredient(double amount, Ingredient ingredient) {
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public AmountOfIngredient() {
    }

    @Override
    public String toString() {
        return String.format("%.1f %s", this.amount, this.ingredient);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

//    public Recipe getRecipe() {
//        return recipe;
//    }
//
//    public void setRecipe(Recipe recipe) {
//        this.recipe = recipe;
//    }
}
