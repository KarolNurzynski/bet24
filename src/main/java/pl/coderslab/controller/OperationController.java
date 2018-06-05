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

        Account account = accountService.findAccountByUserId(user_id);

        if (account==null) {
            return "redirect:/account/add";
        }

        List<Operation> accountOperations = operationService.findAllOperationsByAccountId(account.getId());

        BigDecimal accountSummary = operationService.getSumOfAllOperationsValueByAccountId(account.getId());


        model.addAttribute("accountOperations", accountOperations);
        model.addAttribute("accountSummary", accountSummary);
        model.addAttribute("operation", new Operation());
        return "operationListAll";
    }

    @PostMapping("/add")
    public String addOperation(@Valid @ModelAttribute Operation operation,
                               BindingResult result,
                               HttpSession sess){
        System.out.println("=======post====");
        if (result.hasErrors()) {
            return "operationListAll";
        }
        Long user_id = (Long) sess.getAttribute("user_id");
        operation.setAccount(accountService.findAccountByUserId(user_id));
        operation.setDate(LocalDateTime.now());
        operation.setDescription("Account recharged by user");
        operationService.saveOperation(operation);
        return "redirect:/operation/add";
    }

    @GetMapping("/show/all")
    public String showAllOperations(Model model) {
        return "operationListAll";
    }

    @GetMapping("/show/{operation_id")
    public String showOperation(@PathVariable Long operation_id, Model model) {
        model.addAttribute(operationService.findOperationById(operation_id));
        return "operationShow";
    }


//    @GetMapping("/edit/{operation_id}")
//    public String editOperation(@PathVariable Long operation_id, Model model) {
//        Operation operation = operationService.findOperationById(operation_id);
//        model.addAttribute("operation",operation);
//        return "operationEditForm";
//    }
//
//    @PostMapping("/edit/{operation_id}")
//    public String editOperation(@ModelAttribute Operation operation,
//                           @PathVariable Long operation_id,
//                           Model model) {
//        operation.setId(operation_id);
//        operationService.editOperation(operation);
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{operation_id}")
//    public String deleteOperation(@PathVariable Long operation_id, Model model) {
//        operationService.deleteOperation(operation_id);
//        return "redirect:/";
//    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

    @ModelAttribute("operations")
    public List<Operation> operations() {
        return operationService.findAllOperations();
    }

}
