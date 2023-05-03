package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peaksoft.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/")
public class ViewController {
    private final UserServiceImpl userService;

    public ViewController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping() // возвращает начальную страницу
    public String getMain() {
        return "index";
    }
    @GetMapping("login")
    public String login() {
        return "login";
    }

//    @GetMapping("company")
//    public String company() {
//        return "company/companies";
//    }
//    @GetMapping("addUser")
//    public String addUser(Model model){
//        model.addAttribute("user", new User());
//        return "user/saveUser";
//    }
//
//    @PostMapping("saveUser")
//    public String saveUser(@ModelAttribute("user") User user) { // user был возврашен к нам с метода (adduser)
//        userService.addUser(user);
//        return "user/saveUser";
//    }


}
