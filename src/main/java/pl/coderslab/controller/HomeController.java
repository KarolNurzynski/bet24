package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;
import pl.coderslab.validation.ValidUsernamePassword;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
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
        return "redirect:/home";//nie dziala - Spring to blokuje
    }

    @GetMapping("/home")
    public String homeView(Model model,
                           HttpSession sess) {

        Long user_id = (Long) sess.getAttribute("user_id");
        List<Bet> cart = (List<Bet>) sess.getAttribute("cartOfBets");

        BigDecimal totalToPay = BigDecimal.valueOf(0);
        BigDecimal totalToWin = BigDecimal.valueOf(0);

        if (cart!=null) {
            if (!cart.isEmpty()) {

                for (Bet bet : cart) {
                    totalToPay = totalToPay.add(bet.getStake());
                    totalToWin = totalToWin.add(bet.getStake().multiply(bet.getBetOffer().getOdds()));
                }

                model.addAttribute("totalToPay", totalToPay);
                model.addAttribute("totalToWin", totalToWin);
            } else {
                totalToPay = BigDecimal.valueOf(0);
                totalToWin = BigDecimal.valueOf(0);
            }
        }


        List<Bet> activeUserBets = betService.findAllActiveBetsByUserId(user_id);
        model.addAttribute("activeUserBets", activeUserBets);
        model.addAttribute("user", new User());
        return "homeView";
    }

    //at this point I add to session user_id and a cart (to store bets)
    @PostMapping("/home")
    public String login(@Validated({ValidUsernamePassword.class}) @ModelAttribute User user,
                        BindingResult result,
                        HttpSession sess,
                        Model model) {

        if (result.hasErrors()) {
            return "login";
        }

        User userFromDBByUsername = userService.findUserByUsername(user.getUsername());
        if ((userFromDBByUsername == null) ||
                (!BCrypt.checkpw(user.getPassword(), userFromDBByUsername.getPassword()))) {
            model.addAttribute("loginError",1);
            return "login";
        } else {
            Long user_id = userFromDBByUsername.getId();

            List<Bet> cartOfBets = new ArrayList<>();
            sess.setAttribute("user_id",user_id);
            sess.setMaxInactiveInterval(120);

            sess.setAttribute("cartOfBets", cartOfBets);

            model.addAttribute("betOffer", new BetOffer());
            model.addAttribute("user", user);
            return "redirect:/home";
        }
    }

    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("betOfferTypes")
    public List<BetOfferType> allBetOfferTypes() {
        return betOfferTypeService.findAllBetOfferTypes();
    }

    /////////////////////////    NOT STANDARD MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("betOffers")
    public List<BetOffer> allBetOffers() {
        return betOfferService.findAllActiveBetOffers();
    }

    @ModelAttribute("events")
    public List<Event> events() {
        return eventService.findAllActiveEvents();
    }



}
