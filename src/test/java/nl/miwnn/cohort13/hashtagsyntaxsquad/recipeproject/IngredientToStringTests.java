package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.UnitOfMeasurement;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IngredientToStringTests {

	@Test
	@DisplayName("Test toString Ingredient with Name and UnitOfMeasurement")
	void testToStringIngredientWithNameAndUnitOfMeasurement() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName("Knäckebröd");
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.PIECE);

		// Act
		String expectedString = "Knäckebröd (piece)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient Uppercase and Lowercase")
	void testToStringIngredientUppercaseAndLowercase() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName("CoTTAgE cHeeSe");
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.GRAM);

		// Act
		String expectedString = "Cottage cheese (gram)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient UnitOfMeasurementOnly ")
	void testToStringIngredientUnitOfMeasurementOnly() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName(null);
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.LITER);

		// Act
		String expectedString = "Null (liter)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient NameOnly and Uppercase")
	void testToStringIngredientNameOnlyAndUppercase() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName("CASHEWS");
		testIngredient.setUnitOfMeasurement(null);

		// Act
		String expectedString = "Cashews (null)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient NameOnly and Lowercase Ingredient")
	void testToStringIngredientIngredientNameOnlyAndLowercase() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName("water");
		testIngredient.setUnitOfMeasurement(null);

		// Act
		String expectedString = "Water (null)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient Empty")
	void testToStringIngredientEmpty() {

		// Arrange
		Ingredient testIngredient = new Ingredient();

		// Act
		String expectedString = "Null (null)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}
}
