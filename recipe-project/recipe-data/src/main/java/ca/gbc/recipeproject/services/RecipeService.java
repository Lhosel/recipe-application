package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.model.Recipe;

public interface RecipeService extends CrudService<Recipe, Long>{

    Recipe findByRecipename(String recipeName);

}
