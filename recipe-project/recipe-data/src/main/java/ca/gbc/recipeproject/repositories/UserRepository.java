package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
