package pl.coderslab.event24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.event24.Event24;
import pl.coderslab.event24.Event24Service;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/event24")
public class Event24Controller {

    @Autowired
    Event24Service event24Service;

    @GetMapping("/add")
    public String addEvent24(Model model){
        model.addAttribute("event24", new Event24());
        return "event24/adminEvent24ListAll";
    }

    @PostMapping("/add")
    public String addEvent24(@Valid @ModelAttribute Event24 event24,
                             BindingResult result){
        if (result.hasErrors()) {
            return "event24/adminEvent24ListAll";
        }
        event24Service.saveEvent24(event24);
        return "redirect:/event24/add";
    }

    @GetMapping("/edit/{event24_id}")
    public String editEvent24(@PathVariable Long event24_id,
                                   Model model) {
        Event24 event24 = event24Service.findEvent24ById(event24_id);
        model.addAttribute("event24", event24);
        return "event24/adminEvent24EditForm";
    }

    @PostMapping("/edit/{event24_id}")
    public String editEvent24(@ModelAttribute Event24 event24,
                           @PathVariable Long event24_id,
                           Model model) {
        event24Service.editEvent24(event24);
        return "redirect:/event24/add";
    }

    @GetMapping("/delete/{event24_id}")
    public String deleteEvent24(@PathVariable Long event24_id, Model model) {
        event24Service.deleteEvent24(event24_id);
        return "redirect:/event24/add";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("event24s")
    public List<Event24> event24s() {
        return event24Service.findAllEvent24();
    }

}
