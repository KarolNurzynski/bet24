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
import java.util.List;

/**
 * Controller which manages CRUD actions on user accounts
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

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
    public String addAccount(Model model,
                             HttpSession sess){
        Long user_id = (Long) sess.getAttribute("user_id");
        User user = userService.findUserById(user_id);

        List<Account> accounts = accountService.findAllAccountsByUserId(user_id);

        if (accounts!=null) {
            model.addAttribute("accounts", accounts);
        }

        model.addAttribute("account", new Account());

        return "accountListAll";
    }

    @PostMapping("/add")
    public String addAccount(@Valid @ModelAttribute Account account,
                             BindingResult result,
                             HttpSession sess,
                             Model model){
        if (result.hasErrors()) {
            return "accountListAll";
        }

        Long user_id = (Long) sess.getAttribute("user_id");
        User user = userService.findUserById(user_id);

        accountService.activateUserAccount(user_id, account);

        return "redirect:/account/add";
    }

    @GetMapping("/activate/{account_id}")
    public String activateAccount(Model model,
                             HttpSession sess,
                                  @PathVariable Long account_id){

        Long user_id = (Long) sess.getAttribute("user_id");
        User user = userService.findUserById(user_id);
        Account account = accountService.findAccountById(account_id);

        model.addAttribute("account", new Account());

        accountService.activateUserAccount(user_id, account);

        return "redirect:/account/add";
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
