package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.*;

/**
 * @author Sami ARSLAN
 * <p>
 * Deals with favorite recipe
 **/

@Entity
public class FavoriteRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;
    private Long userId;
    private Long id;

    public FavoriteRecipe(Long favoriteId, Long userId, Long id) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.id = id;
    }

    public FavoriteRecipe() {
    }

    public FavoriteRecipe(Long userId, Long id) {
        this.userId = userId;
        this.id = id;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
