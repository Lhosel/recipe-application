package ca.gbc.recipeproject.services.map;

import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.services.RecipeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class RecipeServiceMap extends AbstractMapService<Recipe, Long> implements RecipeService {

    @Override
    public Set<Recipe> findAll() {
        return super.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Recipe save(Recipe object) {
        return super.save(object);
    }

    @Override
    public void delete(Recipe object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Recipe findByRecipename(String recipeName) {
        return null;
    }

}
