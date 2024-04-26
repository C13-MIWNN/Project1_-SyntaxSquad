package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

/**
 * @author Sami ARSLAN
 * Deals with favorite recipe
 **/

@Entity
public class FavoriteRecipe {
    @Id
    @GeneratedValue
    private Long favoriteId;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.MERGE)
    private RecipeUser recipeUser;

    public FavoriteRecipe(Long favoriteId, Recipe recipe, RecipeUser recipeUser) {
        this.favoriteId = favoriteId;
        this.recipe = recipe;
        this.recipeUser = recipeUser;
    }

    public FavoriteRecipe() {
    }

    public FavoriteRecipe(Recipe recipe, RecipeUser recipeUser) {
        this.recipe = recipe;
        this.recipeUser = recipeUser;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
