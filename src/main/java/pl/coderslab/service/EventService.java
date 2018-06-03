package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.serviceInterface.EventServiceInterface;

import java.util.List;

@Service
public class EventService implements EventServiceInterface {

    EventRepository eventRepository;

    @Autowired
    EventService(EventRepository eventRepository) {
        this.eventRepository=eventRepository;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElseGet(null);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event editEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }



}
