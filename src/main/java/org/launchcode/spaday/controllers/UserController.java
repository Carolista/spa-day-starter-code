package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    // This was not in the starter code but is necessary for user/index to load
    // Empty user object added to try to load username when redirecting from add form submission
//    @GetMapping
//    public String displayUserPage(Model model) {
//        model.addAttribute("user", new User());
//        return "user/index";
//    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute("user",new User());
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
            return "user/index";
        }

    }


}
