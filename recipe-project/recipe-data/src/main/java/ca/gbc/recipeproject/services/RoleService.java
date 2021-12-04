package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.model.Role;

import java.util.Set;

public interface RoleService extends CrudService<Role, Long> {

    Set<Role> findByName(String user);

}
