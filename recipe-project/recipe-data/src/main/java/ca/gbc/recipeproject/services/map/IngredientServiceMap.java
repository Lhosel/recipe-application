package ca.gbc.recipeproject.services.map;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.services.IngredientService;

import java.util.Set;

public class IngredientServiceMap extends AbstractMapService<Ingredient, Long> implements IngredientService {

    @Override
    public Set<Ingredient> findAll() {
        return super.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Ingredient save(Ingredient object) {
        return super.save(object);
    }

    @Override
    public void delete(Ingredient object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
