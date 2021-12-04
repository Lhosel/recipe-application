package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.repositories.IngredientRepository;
import ca.gbc.recipeproject.services.IngredientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class IngredientSDJpaService implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientSDJpaService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> findAll() {

        Set<Ingredient> ingredients = new HashSet<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        return ingredients;

    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public Ingredient save(Ingredient object) {
        return ingredientRepository.save(object);
    }

    @Override
    public void delete(Ingredient object) {
        ingredientRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

}
