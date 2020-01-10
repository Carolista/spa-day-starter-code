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

    // Handle user/index.html

    @GetMapping
    public String displayAllUsers(Model model) {
        model.addAttribute("title", "All Users");
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    // Handle user/add.html

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute @Valid User user, Errors errors, Model model, String verify) {
        if (! verify.equals(user.getPassword())) {
            model.addAttribute("matchError", "Passwords do not match");
            return "user/add";
        } else if (errors.hasErrors()) {
            return "user/add";
        } else {
            model.addAttribute("user",user);
            UserData.add(user);
            return "user/index";
        }

    }


}
