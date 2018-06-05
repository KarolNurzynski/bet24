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
    public String addAccount(Model model){
        model.addAttribute("account", new Account());
        return "accountForm";
    }

    @PostMapping("/add")
    public String addAccount(@Valid @ModelAttribute Account account,
                             BindingResult result,
                             HttpSession sess){
//        if (result.hasErrors()) {
//            return "accountForm";
//        }
//        Long user_id = (Long) sess.getAttribute("user_id");
//
//        User user = userService.findUserById(user_id);
//        user.setAccounts(account);
//        userService.editUser(user);
        return "redirect:/home";
    }

//    @GetMapping("/show/all")
//    public String showAllAccounts(Model model) {
//        return "accountListAll";
//    }
//
//    @GetMapping("/show/{account_id")
//    public String showAccount(@PathVariable Long account_id, Model model) {
//        model.addAttribute(accountService.findAccountById(account_id));
//        return "accountShow";
//    }


//    @GetMapping("/edit/{account_id}")
//    public String editAccount(@PathVariable Long account_id, Model model) {
//        Account account = accountService.findAccountById(account_id);
//        model.addAttribute("account",account);
//        return "accountEditForm";
//    }
//
//    @PostMapping("/edit/{account_id}")
//    public String editAccount(@ModelAttribute Account account,
//                           @PathVariable Long account_id,
//                           Model model) {
//        account.setId(account_id);
//        accountService.editAccount(account);
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{account_id}")
//    public String deleteAccount(@PathVariable Long account_id, Model model) {
//        accountService.deleteAccount(account_id);
//        return "redirect:/";
//    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("accounts")
    public List<Account> accounts() {
        return accountService.findAllAccounts();
    }

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
