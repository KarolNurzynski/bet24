package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.entity.Event;
import pl.coderslab.service.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/betOffer")
public class BetOfferController {

    @Autowired
    BetService betService;

    @Autowired
    BetOfferService betOfferService;

    @Autowired
    BetOfferTypeService betOfferTypeService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @GetMapping("/add")
    public String addBetOffer(Model model){
        model.addAttribute("betOffer", new BetOffer());
        return "adminBetOfferListAll";
    }

    @PostMapping("/add")
    public String addBetOffer(@Valid @ModelAttribute BetOffer betOffer,
                             BindingResult result){
        if (result.hasErrors()) {
            return "adminBetOfferListAll";
        }
        betOffer.setPublished(LocalDateTime.now());
        betOfferService.saveBetOffer(betOffer);
        return "redirect:/betOffer/add";
    }

    @GetMapping("/edit/{betOffer_id}")
    public String editBetOffer(@PathVariable Long betOffer_id,
                                   Model model) {
        BetOffer betOffer = betOfferService.findBetOfferById(betOffer_id);
        model.addAttribute("betOffer", betOffer);
        return "adminBetOfferEditForm";
    }

    @PostMapping("/edit/{betOffer_id}")
    public String editBetOffer(@ModelAttribute BetOffer betOffer,
                           @PathVariable Long betOffer_id,
                           Model model) {
        betOfferService.editBetOffer(betOffer);
        return "redirect:/betOffer/add";
    }

    @GetMapping("/delete/{betOffer_id}")
    public String deleteBetOffer(@PathVariable Long betOffer_id, Model model) {
        betOfferService.deleteBetOffer(betOffer_id);
        return "redirect:/betOffer/add";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("events")
    public List<Event> events() {
        return eventService.findAllEvents();
    }


    @ModelAttribute("betOfferTypes")
    public List<BetOfferType> allBetOfferTypes() {
        return betOfferTypeService.findAllBetOfferTypes();
    }

    @ModelAttribute("betOffers")
    public List<BetOffer> allBetOffers() {
        return betOfferService.findAllBetOffers();
    }

}
