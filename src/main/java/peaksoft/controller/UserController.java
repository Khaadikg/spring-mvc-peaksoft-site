package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.User;
import peaksoft.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "user/users";
    }
    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "user/saveUser";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user")User user){
        userService.addUser(user);
        return "redirect:/user/users";
    }
    @GetMapping("/userUpdate/{id}")
    public String updateUser(@PathVariable("id")Long id,Model model){
        User user = userService.getUserById(id);
        model.addAttribute("updateUser",user);
        return "user/userUpdate";
    }
    @PatchMapping("/{id}")
    public String saveUserUpdate(@PathVariable("id")Long id,@ModelAttribute("updateUser")User user){
        userService.updateUser(id,user);
        return "redirect:/user/users";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id")Long id){
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/user/users";
    }
}
