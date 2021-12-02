package ca.gbc.recipeproject.services.map;

import ca.gbc.recipeproject.model.Meal;
import ca.gbc.recipeproject.services.MealService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MealServiceMap extends AbstractMapService<Meal, Long> implements MealService {

    @Override
    public Set<Meal> findAll() {
        return super.findAll();
    }

    @Override
    public Meal findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Meal save(Meal object) {
        return super.save(object);
    }

    @Override
    public void delete(Meal object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
