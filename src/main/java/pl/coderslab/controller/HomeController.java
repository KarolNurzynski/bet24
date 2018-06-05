package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

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

    //if not logged in, redirected to address "/login" (By Spring Sec); login url redirects to "/" if logged in with success
    @GetMapping("/")
    public String home() {
        return "homeView";
    }

    @GetMapping("/home")
    public String homeView() {
        return "homeView";
    }

    //at this point I add to session user_id and a cart (to store bets)
    @PostMapping("/home")
    public String login(@ModelAttribute User user,
                        HttpSession sess,
                        Model model) {
//        if (result.hasErrors()) {
//            return "loginPage";
//        }
        User userFromDB = userService.findByUserName(user.getUsername());
        if ((userFromDB == null) || (!BCrypt.checkpw(user.getPassword(), userFromDB.getPassword()))) {
            model.addAttribute("loginError",1);
            return "login";
        } else {
            Long user_id = userFromDB.getId();

            List<Bet> cartOfBets = new ArrayList<>();
            sess.setAttribute("user_id",user_id);
            sess.setAttribute("cartOfBets", cartOfBets);
            sess.setMaxInactiveInterval(120);
            model.addAttribute("betOffer", new BetOffer());
            model.addAttribute("user", user);
            return "homeView";
        }
    }

    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

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
