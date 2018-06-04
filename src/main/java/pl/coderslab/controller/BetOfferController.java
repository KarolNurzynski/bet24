package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.entity.Event;
import pl.coderslab.service.*;

import javax.servlet.http.HttpSession;
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
    public String addBet(Model model){
        model.addAttribute("betOffer", new BetOffer());
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addBetOffer(@ModelAttribute BetOffer betOffer){
//        if (result.hasErrors()) {
//            return "redirect:/";
//        }
        betOffer.setPublished(LocalDateTime.now());
        betOfferService.saveBetOffer(betOffer);
        return "redirect:/";
    }

    @GetMapping("/show/all")
    public String showAllBets(Model model) {
        return "betOfferListAll";
    }
//
//    @GetMapping("/show/{bet_id")
//    public String showBet(@PathVariable Long bet_id, Model model) {
//        model.addAttribute(betService.findBetById(bet_id));
//        return "betShow";
//    }
//
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

//    @ModelAttribute("users")
//    public List<User> users() {
//        return userServiceInterface.findAllUsers();
//    }
//
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
