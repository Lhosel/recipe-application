package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {

}
