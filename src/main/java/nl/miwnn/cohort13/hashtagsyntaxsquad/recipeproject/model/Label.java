package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *@author #SyntaxSquad
 * Represents a category/label that characterises a recipe
 **/

@Entity
public class Label {


    @Id
    private String idLabel;


    public void setIdLabel(String idLabel) {
        this.idLabel = idLabel;
    }

    public String getIdLabel() {
        return idLabel;
    }


}
