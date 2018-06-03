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
import pl.coderslab.service.BetService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addBet(Model model){
        model.addAttribute("bet", new Bet());
        return "betForm";
    }

    @PostMapping("/add")
    public String addBet(@Valid @ModelAttribute Bet bet,
                             BindingResult result){
        if (result.hasErrors()) {
            return "betForm";
        }
//        bet.setStar(LocalDateTime.now());
        betService.saveBet(bet);
        return "redirect:/";
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

}
