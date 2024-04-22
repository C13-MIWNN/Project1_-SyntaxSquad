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

    // @Column(nullable = false)
    private String recipeName;
//    private int numberOfPortions;

    @ElementCollection
    private List<String> instructions = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    // @Column(nullable = false)
    private List<IngredientInRecipe> ingredientInRecipeList = new ArrayList<>();

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    public Recipe(String recipeName,
                  List<String> instructions,
                  List<IngredientInRecipe> ingredientInRecipeList,
                  List<Tag> tags) {
        this.recipeName = recipeName;
        this.instructions = instructions;
        this.ingredientInRecipeList = ingredientInRecipeList;
        this.tags = tags;
    }

    public Recipe(String recipeName,
                  List<IngredientInRecipe> ingredientInRecipeList,
                  List<String> instructions,
                  List<Tag> tags,
                  byte[] imageData) {
        this.recipeName = recipeName;
        this.ingredientInRecipeList = ingredientInRecipeList;
        this.instructions = instructions;
        this.tags = tags;
        this.imageData = imageData;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
