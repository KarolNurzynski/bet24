package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import java.util.List;

/**
 * Controller mapping /login url
 */
@Controller
public class LoginController {

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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new User());
        return "login";
    }

    /////////////////////// MODEL ATTRIBUTES //////////////////////////

    @ModelAttribute("events")
    public List<Event> events() {
        return eventService.findAllEvents();
    }

    @ModelAttribute("bets")
    public List<Bet> bets() {
        return betService.findAllBets();
    }

    @ModelAttribute("events")
    public List<Event> allEvents() {
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