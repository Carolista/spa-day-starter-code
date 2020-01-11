package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    private boolean match = true;

    // Handle user/index.html - not necessary since processAddUserForm handles it and page is not meant to be accessed directly

//    @GetMapping
//    public String displayAllUsers(Model model) {
//        model.addAttribute("title", "All Users");
//        model.addAttribute("users", UserData.getAll());
//        return "user/index";
//    }

    // Handle user/add.html

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("page", "Viv Day Spa :: Create Your Account");
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid User user, Errors errors, Model model, String verify) {
        if (! verify.equals(user.getPassword())) {
            model.addAttribute("matchError", "Passwords do not match");
            model.addAttribute("page", "Viv Day Spa :: Create Your Account");
            return "user/add";
        } else if (errors.hasErrors()) {
            model.addAttribute("page", "Viv Day Spa :: Create Your Account");
            return "user/add";
        } else {
            UserData.add(user);
            model.addAttribute("user",user);
            model.addAttribute("users", UserData.getAll());
            model.addAttribute("page", "Viv Day Spa :: Welcome");
            return "user/index";
        }

    }

    @RequestMapping("details")
    public String displayUserDetails(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("page", "Viv Day Spa :: User Details");
        return "user/details";
    }

}
