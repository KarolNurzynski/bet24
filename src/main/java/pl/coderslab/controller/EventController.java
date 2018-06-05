package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/add")
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "adminEventListAll";
    }

    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute Event event,
                             BindingResult result){
        if (result.hasErrors()) {
            return "adminEventListAll";
        }
        eventService.saveEvent(event);
        return "redirect:/event/add";
    }

    @GetMapping("/edit/{event_id}")
    public String editEvent(@PathVariable Long event_id,
                                   Model model) {
        Event event = eventService.findEventById(event_id);
        model.addAttribute("event", event);
        return "adminEventEditForm";
    }

    @PostMapping("/edit/{event_id}")
    public String editEvent(@ModelAttribute Event event,
                           @PathVariable Long event_id,
                           Model model) {
        eventService.editEvent(event);
        return "redirect:/event/add";
    }

    @GetMapping("/delete/{event_id}")
    public String deleteEvent(@PathVariable Long event_id, Model model) {
        eventService.deleteEvent(event_id);
        return "redirect:/event/add";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("events")
    public List<Event> events() {
        return eventService.findAllEvents();
    }

}
