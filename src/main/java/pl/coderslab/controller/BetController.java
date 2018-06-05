package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bet")
public class BetController {

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

    @GetMapping("/add/{betOffer_id}")
    public String addBet(@PathVariable Long betOffer_id,
                         Model model){
        model.addAttribute("bet", new Bet());
        model.addAttribute("betOffer",
                betOfferService.findBetOfferById(betOffer_id));
        return "betForm";
    }

    @PostMapping("/add/{betOffer_id}")
    public String addBet(@ModelAttribute Bet bet,
                         @PathVariable Long betOffer_id,
                         HttpSession sess){
//        if (result.hasErrors()) {
//            return "betForm";
//        }

        Long user_id = (Long) sess.getAttribute("user_id");
        List<Bet> cart = (List<Bet>) sess.getAttribute("cartOfBets");

        BetOffer betOffer= betOfferService.findBetOfferById(betOffer_id);
        Event event = betOffer.getEvent();
        User user = userService.findUserById(user_id);

        bet.setBetOffer(betOffer);
        bet.setUser(user);
        bet.setEvent(event);
        bet.setTimeMade(LocalDateTime.now());

        cart.add(bet);

        List<Bet> cart2 = (List<Bet>) sess.getAttribute("cartOfBets");

        return "redirect:/home";
    }

    @GetMapping("/add/cartOfBets")
    public String addcartOfBets(HttpSession sess){
        List<Bet> cartOfBets = (List<Bet>) sess.getAttribute("cartOfBets");
        betService.saveListOfBets(cartOfBets);

        cartOfBets.clear();
        sess.setAttribute("cartOfBets",cartOfBets);
        return "redirect:/home";
    }


//    @GetMapping("/show/all")
//    public String showAllBets(Model model) {
//        return "betListAll";
//    }
//
//    @GetMapping("/show/{bet_id")
//    public String showBet(@PathVariable Long bet_id, Model model) {
//        model.addAttribute(betService.findBetById(bet_id));
//        return "betShow";
//    }


//    @GetMapping("/edit/{bet_id}")
//    public String editBet(@PathVariable Long bet_id, Model model) {
//        Bet bet = betService.findBetById(bet_id);
//        model.addAttribute("bet",bet);
//        return "betEditForm";
//    }
//
//    @PostMapping("/edit/{bet_id}")
//    public String editBet(@ModelAttribute Bet bet,
//                           @PathVariable Long bet_id,
//                           Model model) {
//        bet.setId(bet_id);
//        betService.editBet(bet);
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{bet_id}")
//    public String deleteBet(@PathVariable Long bet_id, Model model) {
//        betService.deleteBet(bet_id);
//        return "redirect:/";
//    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

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
