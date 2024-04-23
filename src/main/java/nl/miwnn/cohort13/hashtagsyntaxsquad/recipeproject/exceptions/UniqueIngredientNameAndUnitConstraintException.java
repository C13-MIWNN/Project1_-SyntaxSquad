package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.exceptions;

/**
 * @author Sarah Fopma
 * Exception for when a user tries to enter a combination of Ingredient Name and Unit of Measurement
 * that has already been added to the database.
 **/
public class UniqueIngredientNameAndUnitConstraintException extends Exception {

    public UniqueIngredientNameAndUnitConstraintException(String message) {
        super(message);
    }
}
