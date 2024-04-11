package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

/**
 *@author #SyntaxSquad
 * Represents a tag that can be assigned to a recipe
 **/

@Entity
public class Tag {

    @Id
    private String tag;

    @Override
    public String toString() {
        return tag;
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag() {
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
