package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Account;
import pl.coderslab.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/add")
    public String addAccount(Model model){
        model.addAttribute("account", new Account());
        return "accountForm";
    }

    @PostMapping("/add")
    public String addAccount(@Valid @ModelAttribute Account account,
                             BindingResult result){
        if (result.hasErrors()) {
            return "accountForm";
        }
//        account.setPublished(LocalDateTime.now());
//        account.setAccountStatus(1);
        accountService.saveAccount(account);
        return "redirect:/account/show/all";
    }

    @GetMapping("/show/all")
    public String showAllAccounts(Model model) {
        return "accountListAll";
    }

    @GetMapping("/show/{account_id")
    public String showAccount(@PathVariable Long account_id, Model model) {
        model.addAttribute(accountService.findAccountById(account_id));
        return "accountShow";
    }


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

}
