package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService userService;

//    @GetMapping("/")
//    public List<User> listOfUsers() {
//        return userServiceImpl.findAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userServiceImpl.findUserById(id);
//    }
//
//    @PostMapping("/")
//    public void addUser(@RequestBody User user)
//    {
//        userServiceImpl.saveUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public void editUser(@RequestBody User user) {
//        userServiceImpl.editUser(user);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userServiceImpl.deleteUser(id);
//    }


}
