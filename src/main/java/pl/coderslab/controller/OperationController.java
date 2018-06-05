package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Account;
import pl.coderslab.entity.Operation;
import pl.coderslab.service.AccountService;
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


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("operations")
    public List<Operation> operations() {
        return operationService.findAllOperations();
    }

}
