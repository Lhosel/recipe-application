package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.repositories.RecipeRepository;
import ca.gbc.recipeproject.services.RecipeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class RecipeSDJpaService implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeSDJpaService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe object) {
        return recipeRepository.save(object);
    }

    @Override
    public void delete(Recipe object) {
        recipeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe findByRecipename(String recipeName) {
        return null;
    }
}
