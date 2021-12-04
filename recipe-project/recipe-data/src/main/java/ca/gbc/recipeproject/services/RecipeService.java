package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.model.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService extends CrudService<Recipe, Long>{

    Recipe findByRecipename(String recipeName);

    Set<Recipe> findByName(String searchWord);

    List<Recipe> findByUsername(String username);

}
