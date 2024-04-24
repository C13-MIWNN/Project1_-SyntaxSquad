package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;/*
 * @author Henk-Jan Veld
 * Represents an ingredient that can be added to a recipe
 **/

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UniqueIngredientNameAndUnitConstraint",
        columnNames = {"name", "unitOfMeasurement"}))
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Ingredient name field cannot be empty.")
    private String name;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasurement unitOfMeasurement;

    public Ingredient(Long id, String name, UnitOfMeasurement unitOfMeasurement) {
        this.id = id;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Ingredient(String name, UnitOfMeasurement unitOfMeasurement) {
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Ingredient() {
    }

    @Override
    public String toString() {
        String ingredientString = name + " (" + unitOfMeasurement + ")";
        return ingredientString.substring(0 ,1).toUpperCase() + ingredientString.substring(1).toLowerCase();
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
}
