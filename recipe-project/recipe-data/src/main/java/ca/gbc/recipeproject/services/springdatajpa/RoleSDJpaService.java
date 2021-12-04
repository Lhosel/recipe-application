package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.model.Role;
import ca.gbc.recipeproject.repositories.RoleRepository;
import ca.gbc.recipeproject.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class RoleSDJpaService implements RoleService {

    private final RoleRepository roleRepository;

    public RoleSDJpaService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {

        Set<Role> roles = new HashSet<>();
        roleRepository.findAll().forEach(roles::add);

        return roles;

    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Set<Role> findByName(String user) {
        return roleRepository.findByName(user);
    }

}
