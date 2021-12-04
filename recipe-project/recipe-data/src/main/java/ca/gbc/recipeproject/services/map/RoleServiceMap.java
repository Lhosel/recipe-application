package ca.gbc.recipeproject.services.map;

import ca.gbc.recipeproject.model.Role;
import ca.gbc.recipeproject.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class RoleServiceMap extends AbstractMapService<Role, Long> implements RoleService {

    @Override
    public Set<Role> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Role object) {
        super.delete(object);
    }

    @Override
    public Role save(Role object) {
        return super.save(object);
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Role> findByName(String user) {
        return null;
    }

}
