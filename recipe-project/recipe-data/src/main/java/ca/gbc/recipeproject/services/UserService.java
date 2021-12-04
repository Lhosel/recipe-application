package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.model.User;

public interface UserService extends CrudService<User, Long>{

    User getUserByUsername(String username);

    User getUserById(Long id);

}
