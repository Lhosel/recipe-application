package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.Events;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<Events, Long> {

}
