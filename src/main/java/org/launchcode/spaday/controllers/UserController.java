package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String processAddUserForm(Model model, @ModelAttribute User user, String
            confirmPW) {
        model.addAttribute("match", match);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        if (! confirmPW.equals(user.getPassword())) {
            match = false;
            return "user/add";
        }
        UserData.add(user);
        return "redirect:";
    }

}