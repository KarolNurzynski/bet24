package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.EventDto;
import pl.coderslab.dto.LiveEventDto;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller prodviding external users with basic CRUD operations on events. No security provided.
 */
@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public List<Event> listOfEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @PostMapping("/")
    public void addEvent(@RequestBody EventDto event) {
        eventService.saveEventFromEventDto(event);
    }

    @PutMapping("/{id}")
    public void editEvent(@RequestBody Event event) {
        eventService.editEvent(event);
    }


    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }


    @GetMapping("/active")
    public List<Event> listOfActiveEvents() {
        return eventService.findAllActiveEvents();
    }

    @GetMapping(path = "/live")
    List<LiveEventDto> showActiveLiveEvents() {

        List<LiveEventDto> listOfLiveEvents = new ArrayList<>();

        List<Event> events = eventService.findAllActiveEvents();

        for (Event event : events) {
            listOfLiveEvents.add(new LiveEventDto(event.getTeamA(), (byte) event.getScoreA(), event.getTeamB(), (byte) event.getScoreB()));
        }

        return listOfLiveEvents;

    }

}
