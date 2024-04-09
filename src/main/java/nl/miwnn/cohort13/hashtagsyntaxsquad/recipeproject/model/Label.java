package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
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
