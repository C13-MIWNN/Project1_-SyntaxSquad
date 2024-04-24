package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model;

/**
 * @author Sarah Fopma
 * Enum to set Unit of Measurement value, as part of Ingredient
 **/

public enum UnitOfMeasurement {

    PIECE("piece"),
    GRAM("gram"),
    TEASPOON("teaspoon"),
    TABLESPOON("tablespoon"),
    MILLILITER("milliliter"),
    LITER("liter");

    private final String unitString;

    UnitOfMeasurement(String unitString) {
        this.unitString = unitString;
    }

    @Override
    public String toString() {
        return this.unitString;
    }
}
