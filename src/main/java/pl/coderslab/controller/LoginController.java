package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

//    @GetMapping("/login")
//    public String login() {
//        return "admin/login";
//    }

    @GetMapping("/user/add")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "userForm";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/";
//        User userFromDBByUsername = userService.findByUserName(user.getUsername());
//        if (userFromDBByUsername==null) {
//            userService.saveUser(user);
//            return "redirect:/";
//        } else {
//            return "redirect:/";
//        }
//        User userFromDBByUsername = userServiceInterface.findByUserName(user.getUsername());
//        if (userFromDBByUsername==null) {
//            user.setEnabled(1);
//            user.setRoles(new HashSet<Role>(Arrays.asList(roleService.findRoleById(1L))));
//            userServiceInterface.saveUser(user);
//            return "redirect:/";
//        } else {
//            return "redirect:/";
//        }
    }

}
