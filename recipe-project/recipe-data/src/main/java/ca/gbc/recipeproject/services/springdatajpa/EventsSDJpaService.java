package ca.gbc.recipeproject.services.springdatajpa;

import ca.gbc.recipeproject.model.Events;
import ca.gbc.recipeproject.repositories.EventsRepository;
import ca.gbc.recipeproject.services.EventsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class EventsSDJpaService implements EventsService {

    private final EventsRepository eventsRepository;

    public EventsSDJpaService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Set<Events> findAll() {

        Set<Events> events = new HashSet<>();
        eventsRepository.findAll().forEach(events::add);

        return events;

    }

    @Override
    public Events findById(Long id) {
        return eventsRepository.findById(id).orElse(null);
    }

    @Override
    public Events save(Events object) {
        return eventsRepository.save(object);
    }

    @Override
    public void delete(Events object) {
        eventsRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        eventsRepository.deleteById(id);
    }
}
