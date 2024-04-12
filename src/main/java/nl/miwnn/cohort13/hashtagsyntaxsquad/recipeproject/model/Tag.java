package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *@author #SyntaxSquad
 * Represents a tag that can be assigned to a recipe
 **/

@Entity
public class Tag {

    @Id
    private String tagName;



    @Override
    public String toString() {
        return tagName;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
