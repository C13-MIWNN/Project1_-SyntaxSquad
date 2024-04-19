package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;/*
 * @author Henk-Jan Veld
 * Represents an ingredient that can be added to a recipe
 **/

import jakarta.persistence.*;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.MERGE)
    private List<IngredientInRecipe> ingredientInRecipes = new ArrayList<>();

    public Ingredient(Long id, String name, UnitOfMeasurement unitOfMeasurement,
                      List<IngredientInRecipe> ingredientInRecipes) {
        this.id = id;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.ingredientInRecipes = ingredientInRecipes;
    }

    public Ingredient(String name, UnitOfMeasurement unitOfMeasurement) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Ingredient() {
    }

    @Override
    public String toString() {
        return name + " " + "(" + unitOfMeasurement + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ingredientId) {
        this.id = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public List<IngredientInRecipe> getIngredientInRecipes() {
        return ingredientInRecipes;
    }

    public void setIngredientInRecipes(List<IngredientInRecipe> ingredientInRecipes) {
        this.ingredientInRecipes = ingredientInRecipes;
    }
}
