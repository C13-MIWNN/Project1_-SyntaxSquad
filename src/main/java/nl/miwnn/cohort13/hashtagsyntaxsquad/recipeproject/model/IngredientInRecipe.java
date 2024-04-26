package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

/**
 * @author Sarah Fopma
 * Deals with the amount of each ingredient in a recipe
 **/

@Entity
   public class IngredientInRecipe {

    private static final double LITER_TO_GRAM_CONVERSION_RATE = 1000.0;
    private static final double TABLESPOON_TO_GRAM_CONVERSION_RATE = 15.0;
    private static final double TEASPOON_TO_GRAM_CONVERSION_RATE = 5.0;
    private static final double PIECE_TO_GRAM_CONVERSION_RATE = 100.0;


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

    public double calculateTotalKcal() {
        double totalKcal = 0.0;
        if (ingredient != null) {
            double quantityInGrams = convertToGrams(getAmount(),
                    ingredient.getUnitOfMeasurement());

            double kcalPer100g = ingredient.getkCal();
            totalKcal = (amount * kcalPer100g) / 100.0;
        }
        return totalKcal;
    }

    private double convertToGrams(double quantity, UnitOfMeasurement unit) {
        switch (unit) {
            case PIECE:
                return quantity * PIECE_TO_GRAM_CONVERSION_RATE;
            case GRAM, MILLILITER:
                return quantity;
            case TEASPOON:
                return quantity * TEASPOON_TO_GRAM_CONVERSION_RATE;
            case TABLESPOON:
                return quantity * TABLESPOON_TO_GRAM_CONVERSION_RATE;
            case LITER:
                return quantity * LITER_TO_GRAM_CONVERSION_RATE;
            default:
                return 0.0;
        }
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

