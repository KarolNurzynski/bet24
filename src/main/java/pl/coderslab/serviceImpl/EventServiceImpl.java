package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.EventDto;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.service.EventService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Service with CRUD methods for actions on database table events. All methods names are very descriptive.
 */
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
        return eventRepository.findAllByTimeLeftIsAfter(LocalTime.of(0,0,0));
    }


    public List<Event> findAllFinishedEvents() {
        return eventRepository.findAllByTimeLeftEquals(LocalTime.of(0,0,0));
    }

    @Override
    public List<Event> findAllFinishedEventsWithActiveBets() {
        return eventRepository.findAllFinishedEventsWithActiveBets();
    }

    /**
     *
     * @param eventDto is a data transfer object used to transfer data prob data provider (event24) to this app (bet24)
     * @return is Event entity which is at the same time saved to database
     */
    @Override
    public Event saveEventFromEventDto(EventDto eventDto) {

        Event event = eventRepository.findById(eventDto.getId()).orElse(null);

        if (event==null) {
            event = new Event();
        }

        event.setTeamA(eventDto.getTeamA());
        event.setTeamB(eventDto.getTeamB());
        event.setScoreA(eventDto.getScoreA());
        event.setScoreB(eventDto.getScoreB());
        event.setStrengthA(eventDto.getStrengthA());
        event.setStrengthB(eventDto.getStrengthB());
        event.setStartDate(eventDto.getStartDate());
        event.setTimeLeft(eventDto.getTimeLeft());

        return eventRepository.save(event);
    }


}
