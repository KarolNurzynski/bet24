package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.service.EventService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;

    @Autowired
    EventServiceImpl(EventRepository eventRepository) {
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

    public List<Event> findAllActiveEvents() {
        return eventRepository.findAllByTimeLeftIsBefore(LocalTime.of(0,0,5));
    }

//    @Scheduled(fixedRate = 1000)
//    public List<Event> updateAllActiveEvents() {
//        return null;
//    }


}
