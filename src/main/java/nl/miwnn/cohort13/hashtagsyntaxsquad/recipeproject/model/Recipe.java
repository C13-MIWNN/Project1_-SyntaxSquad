package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author #SyntaxSquad
 * Represents a recipe that has ingredients and instructions to prepare it
 **/
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    private String recipeName;
//    private int numberOfPortions;

    @ElementCollection
    private List<String> instructions = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<IngredientInRecipe> ingredientInRecipeList = new ArrayList<>();

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    public Recipe(Long id, String recipeName,
                  List<IngredientInRecipe> ingredientInRecipeList, List<Tag> tags) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredientInRecipeList = ingredientInRecipeList;
        this.tags = tags;
    }

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

//    public int getNumberOfPortions() {
//        return numberOfPortions;
//    }
//
//    public void setNumberOfPortions(int numberOfPortions) {
//        this.numberOfPortions = numberOfPortions;
//    }

    public List<IngredientInRecipe> getIngredientInRecipeList() {
        return ingredientInRecipeList;
    }

    public void setIngredientInRecipeList(List<IngredientInRecipe> ingredientInRecipeList) {
        this.ingredientInRecipeList = ingredientInRecipeList;
    }

    public void addIngredientsInRecipe(IngredientInRecipe ingredientInRecipe) {
        this.ingredientInRecipeList.add(ingredientInRecipe);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}