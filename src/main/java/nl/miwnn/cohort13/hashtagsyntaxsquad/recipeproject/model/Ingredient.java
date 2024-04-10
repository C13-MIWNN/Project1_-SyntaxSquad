package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;/*
 * @author Henk-Jan Veld
 * Represents an ingredient that can be added to a recipe
 **/

import jakarta.persistence.*;


import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long ingredientId;
    private String name;


    @ManyToMany
    private Set<Recipe> recipes;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient() {

    }

    @Override
    public String toString() {
        return name;
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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;

    }
}
