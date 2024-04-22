package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
