package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public interface EventService {

    public List<Event> findAllEvents();

    public Event findEventById(Long id);

    public Event saveEvent(Event event);

    public Event editEvent(Event event);

    public void deleteEvent(Long id);

    List<Event> findAllActiveEvents();

    List<Event> findAllFinishedEvents();

    List<Event> findAllEventsWithNotEndedBets();

}
