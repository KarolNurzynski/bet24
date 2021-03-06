package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;

/**
 * Controller which manages admin entry points
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/live")
    public String adminLivePage() {
        return "liveResults";
    }

}
