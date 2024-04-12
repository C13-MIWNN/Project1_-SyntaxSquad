package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;/*
 * @author Henk-Jan Veld
 * Represents an ingredient that can be added to a recipe
 **/

import jakarta.persistence.*;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums.UnitOfMeasurement;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long ingredientId;
    private String name;
    private UnitOfMeasurement unitOfMeasurement;

    public Ingredient(String name, UnitOfMeasurement unitOfMeasurement) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Ingredient() {
    }

    @Override
    public String toString() {
        return unitOfMeasurement + " " + name;
    }


    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
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
}
