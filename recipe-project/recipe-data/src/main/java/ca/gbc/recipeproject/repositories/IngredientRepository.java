package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
