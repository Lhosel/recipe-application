package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.view = TRUE")
    Set<Recipe> findPublic();

    @Query("SELECT r FROM Recipe r WHERE lower(r.recipeName) LIKE %:searchWord%")
    Set<Recipe> findByName(@Param("searchWord") String searchWord);

}
