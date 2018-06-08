package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import java.util.List;

/**
 * REST controller prodviding external users with basic CRUD operations on users. No security provided.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> listOfUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user)
    {
        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public void editUser(@RequestBody User user) {
        userService.editUser(user);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
