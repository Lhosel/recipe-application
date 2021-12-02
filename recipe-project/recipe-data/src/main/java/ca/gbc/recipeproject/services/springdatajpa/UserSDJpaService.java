package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.repositories.UserRepository;
import ca.gbc.recipeproject.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {

    private final UserRepository userRepository;

    public UserSDJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {

        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);

        return users;

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
