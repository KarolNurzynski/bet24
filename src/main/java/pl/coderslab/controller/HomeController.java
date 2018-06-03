package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.service.BetService;
import pl.coderslab.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String homeView(){
        return "homeView";
    }


    /////////////////////////    MODEL ATTRIBUTES   /////////////////////////////////

//    @ModelAttribute("users")
//    public List<User> users() {
//        return userService.findAllUsers();
//    }
//
//    @ModelAttribute("bets")
//    public List<Bet> bets() {
//        return betService.findAllBets();
//    }

}
