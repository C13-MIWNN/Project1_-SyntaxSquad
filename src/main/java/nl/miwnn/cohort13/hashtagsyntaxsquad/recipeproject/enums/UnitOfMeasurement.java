package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.enums;

/**
 * @author Sarah Fopma
 * <p>
 * Programma <doet het volgende...>
 **/
public enum UnitOfMeasurement {

    piece,
    gram,
    teaspoon,
    tablespoon,
    milliliter,
    liter,
    ;

    @Override
    public String toString() {
        return switch (this) {
            case piece -> "piece(s)";
            case gram -> "grams";
            case teaspoon -> "teaspoon(s)";
            case tablespoon -> "tablespoon(s)";
            case milliliter -> "milliliter";
            case liter -> "liter";
        };
    }
}
