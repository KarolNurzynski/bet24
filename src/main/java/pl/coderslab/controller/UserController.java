package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

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
    public String addUser(@Valid @ModelAttribute User user,
                          BindingResult result,
                          Model model){
        if (result.hasErrors()) {
            return "userForm";
        }
        User userFromDBByEmail = userService.findUserByEmail(user.getEmail());
        User userFromDBByUsername = userService.findUserByUsername(user.getUsername());
        if ((userFromDBByEmail==null) && (userFromDBByUsername==null)) {
            user.setEnabled(1);
            userService.saveUser(user);
            return "redirect:/";
        } else {
            model.addAttribute("registrationError",1);
            return "userForm";
        }
    }

    @GetMapping("/userprofile/{user_id}")
    public String userPage(@PathVariable Long user_id,
                           Model model,
                           HttpSession sess) {
        Long user_sess_id = (Long) sess.getAttribute("user_id");
        if (user_id==user_sess_id) {
            User user = userService.findUserById(user_id);
            model.addAttribute("user",user);
            return "userProfile";
        } else {
            return "redirect:/userprofile/"+user_sess_id;
        }

    }

}
