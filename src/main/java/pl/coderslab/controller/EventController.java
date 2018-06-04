package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/add")
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "eventForm";
    }

    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute Event event,
                             BindingResult result){
        if (result.hasErrors()) {
            return "eventForm";
        }
//        event.setPublished(LocalDateTime.now());
//        event.setEventStatus(1);
        eventService.saveEvent(event);
        return "redirect:/event/show/all";
    }

    @GetMapping("/show/all")
    public String showAllEvents(Model model) {
        return "eventListAll";
    }

    @GetMapping("/show/{event_id")
    public String showEvent(@PathVariable Long event_id, Model model) {
        model.addAttribute(eventService.findEventById(event_id));
        return "eventShow";
    }


//    @GetMapping("/edit/{event_id}")
//    public String editEvent(@PathVariable Long event_id, Model model) {
//        Event event = eventService.findEventById(event_id);
//        model.addAttribute("event",event);
//        return "eventEditForm";
//    }
//
//    @PostMapping("/edit/{event_id}")
//    public String editEvent(@ModelAttribute Event event,
//                           @PathVariable Long event_id,
//                           Model model) {
//        event.setId(event_id);
//        eventService.editEvent(event);
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{event_id}")
//    public String deleteEvent(@PathVariable Long event_id, Model model) {
//        eventService.deleteEvent(event_id);
//        return "redirect:/";
//    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("events")
    public List<Event> events() {
        return eventService.findAllEvents();
    }

}
