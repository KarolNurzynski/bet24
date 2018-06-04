package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    Validator validator;

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

    // all actions marked with "authenticated" in Spring Sec Config get here
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new User());
        return "login";
    }



    //at this point, if user decides to logout, I cancel him from session
    @GetMapping("/logout")
    public String logout(HttpSession sess) {
        sess.invalidate();
        return "redirect:/";
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