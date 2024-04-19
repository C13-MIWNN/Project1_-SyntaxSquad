package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.mappers;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.dtos.RecipeUserFormDTO;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;

/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
 **/

public class RecipeUserMapper {
    public static RecipeUser fromDTO(RecipeUserFormDTO dto) {
        RecipeUser user = new RecipeUser();
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
