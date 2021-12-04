package ca.gbc.recipeproject.repositories;

import ca.gbc.recipeproject.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Set<Role> findByName(String user);

}
