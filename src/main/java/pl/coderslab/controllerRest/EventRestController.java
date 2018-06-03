package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import java.util.List;

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
    public void addEvent(@RequestBody Event event)
    {
        eventService.saveEvent(event);
    }

    @PutMapping("/{id}")
    public void editEvent(@RequestBody Event event) {
        eventService.editEvent(event);
    }


    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }


}
