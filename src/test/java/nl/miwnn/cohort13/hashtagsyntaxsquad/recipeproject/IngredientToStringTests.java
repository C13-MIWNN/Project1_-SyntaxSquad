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
		testIngredient.setName("Apple");
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.piece);

		// Act
		String expectedString = "Apple (piece)";

		// Assert
		Assertions.assertEquals(expectedString, testIngredient.toString());
	}

	@Test
	@DisplayName("Test toString Ingredient Uppercase and Lowercase")
	void testToStringIngredientUppercaseAndLowercase() {

		// Arrange
		Ingredient testIngredient = new Ingredient();
		testIngredient.setName("CoTTAgE cHeeSe");
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.gram);

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
		testIngredient.setUnitOfMeasurement(UnitOfMeasurement.liter);

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
