package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Account;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.Operation;
import pl.coderslab.entity.User;
import pl.coderslab.service.AccountService;
import pl.coderslab.service.BetService;
import pl.coderslab.service.OperationService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    OperationService operationService;

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @GetMapping("/add")
    public String addOperation(Model model, HttpSession sess){

        Long user_id = (Long) sess.getAttribute("user_id");

        Account account = accountService.findActiveUserAccount(user_id);

        if (account==null) {
            return "redirect:/account/add";
        }

        List<Operation> userOperations = operationService.findAllOperationsByUserId(user_id);

        BigDecimal userOperationsSummary = operationService.getSumOfAllOperationsValueByUserId(user_id);

        model.addAttribute("userOperations", userOperations);
        model.addAttribute("userOperationsSummary", userOperationsSummary);
        model.addAttribute("operation", new Operation());

        return "operationListAll";
    }

    @PostMapping("/add")
    public String addOperation(@Valid @ModelAttribute Operation operation,
                               BindingResult result,
                               HttpSession sess){
        if (result.hasErrors()) {
            return "operationListAll";
        }
        Long user_id = (Long) sess.getAttribute("user_id");
        operation.setAccount(accountService.findActiveUserAccount(user_id));
        operation.setUser(userService.findUserById(user_id));
        operation.setDate(LocalDateTime.now());
        operation.setDescription("Account recharged by user");
        operationService.saveOperation(operation);
        return "redirect:/operation/add";
    }

    @GetMapping("/confirmPayment")
    public String confirmPaymentView(Model model,
                           HttpSession sess) {
        List<Bet> cart = (List<Bet>) sess.getAttribute("cartOfBets");

        BigDecimal totalToPay = BigDecimal.valueOf(0);
        BigDecimal totalToWin = BigDecimal.valueOf(0);

        if (!cart.isEmpty()) {

            for (Bet bet : cart) {
                totalToPay = totalToPay.add(bet.getStake());
                totalToWin = totalToWin.add(bet.getStake().multiply(bet.getBetOffer().getOdds()));
            }

            model.addAttribute("totalToPay", totalToPay);
            model.addAttribute("totalToWin", totalToWin);
        }

        Long user_id = (Long) sess.getAttribute("user_id");
        User user = userService.findUserById(user_id);
        BigDecimal onAccount = operationService.getSumOfAllOperationsValueByUserId(user_id);
        if (onAccount.compareTo(totalToPay)==-1) {
            model.addAttribute("notEnoughMoneyError",1);
            return "operationConfirmPayment";
        }

        model.addAttribute("operation", new Operation());
        model.addAttribute("user", new User());
        return "operationConfirmPayment";
    }

    @PostMapping("/confirmPayment")
    public String confirmPayment(@Valid @ModelAttribute Operation operation,
                                 BindingResult result,
                                 HttpSession sess,
                                 Model model){

        if (result.hasErrors()) {
            return "operationConfirmPayment";
        }

        Long user_id = (Long) sess.getAttribute("user_id");
        User user = userService.findUserById(user_id);
        List<Bet> cartOfBets = (List<Bet>) sess.getAttribute("cartOfBets");

        BigDecimal totalToPay = BigDecimal.valueOf(0);
        for (Bet bet : cartOfBets) {
            totalToPay = totalToPay.add(bet.getStake());
        }


        BigDecimal onAccount = operationService.getSumOfAllOperationsValueByUserId(user_id);

        if (onAccount.compareTo(totalToPay)==1) {
            operation.setUser(userService.findUserById(user_id));
            operation.setDescription("payment for bets placed on: " + LocalDateTime.now());
            operation.setAccount(accountService.findActiveUserAccount(user_id));
            operation.setDate(LocalDateTime.now());
            operation.setValue(totalToPay.negate());
            operationService.saveOperation(operation);
            betService.saveListOfBets(cartOfBets);
            cartOfBets.clear();
            sess.setAttribute("cartOfBets",cartOfBets);
        }
//
//
//        betService.saveListOfBets(cartOfBets);
//
//        cartOfBets.clear();
//        sess.setAttribute("cartOfBets",cartOfBets);
        return "redirect:/home";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("operations")
    public List<Operation> operations() {
        return operationService.findAllOperations();
    }

}
