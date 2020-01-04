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

    boolean match = true;

    // Handle user/index.html

    @GetMapping
    public String displayAllUsers(Model model) {
//        model.addAttribute("title", "All Users");
//        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    // Handle user/add.html

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String
            verify) {
        model.addAttribute("match", match);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        if (verify.equals(user.getPassword())) {
            match = true;
            UserData.add(user);
            return "redirect:";
        } else {
            match = false;
            return "user/add";
        }
    }

}