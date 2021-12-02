package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Meal;
import ca.gbc.recipeproject.repositories.MealRepository;
import ca.gbc.recipeproject.services.MealService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class MealSDJpaService implements MealService {

    private final MealRepository mealRepository;

    public MealSDJpaService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Set<Meal> findAll() {

        Set<Meal> meals = new HashSet<>();
        mealRepository.findAll().forEach(meals::add);

        return meals;

    }

    @Override
    public Meal findById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    @Override
    public Meal save(Meal object) {
        return mealRepository.save(object);
    }

    @Override
    public void delete(Meal object) {
        mealRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }
}
