package ca.gbc.recipeproject.services.map;

import ca.gbc.recipeproject.model.Events;
import ca.gbc.recipeproject.services.EventsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class EventsServiceMap extends AbstractMapService<Events, Long> implements EventsService {

    @Override
    public Set<Events> findAll() {
        return super.findAll();
    }

    @Override
    public Events findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Events save(Events object) {
        return super.save(object);
    }

    @Override
    public void delete(Events object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
