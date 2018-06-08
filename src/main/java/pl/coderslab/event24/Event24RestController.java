package pl.coderslab.event24;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller of external data provider (live events data)
 */
@RestController
@RequestMapping("/api/event24")
public class Event24RestController {

    @Autowired
    Event24Service event24Service;

    @GetMapping("/")
    public List<Event24> listOfEvent24() {
        return event24Service.findAllEvent24();
    }

    @GetMapping("/{id}")
    public Event24 getEvent24ById(@PathVariable Long id) {
        return event24Service.findEvent24ById(id);
    }
    
    @PostMapping("/")
    public void addEvent24(@RequestBody Event24 event24)
    {
        event24Service.saveEvent24(event24);
    }

    @PutMapping("/{id}")
    public void editEvent24(@RequestBody Event24 event24) {
        event24Service.editEvent24(event24);
    }


    @DeleteMapping("/{id}")
    public void deleteEvent24(@PathVariable Long id) {
        event24Service.deleteEvent24(id);
    }


    @GetMapping("/active")
    public List<Event24> listOfActiveEvent24() {
        return event24Service.findAllActiveEvent24();
    }


}
