package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    @GetMapping
    public String displayClientForm (Model model) {
        model.addAttribute("tab", "Viv Day Spa :: Select Your Personalized Services");
        return "serviceSelection";
    }

    @PostMapping
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {
        Client newClient = new Client(name, skintype, manipedi);
        newClient.setAppropriateFacials(skintype);
        model.addAttribute("client" , newClient);
        model.addAttribute("name", name);
        model.addAttribute("manipedi", manipedi);
        model.addAttribute("skintype", skintype);

        ArrayList<String> polishChoices = new ArrayList<>();
        polishChoices.add("#ed553e");
        polishChoices.add("#ed3e4d");
        polishChoices.add("#d12c71");
        polishChoices.add("#a31787");
        polishChoices.add("#34a39e");
        polishChoices.add("#63c295");

        model.addAttribute("polishChoices", polishChoices);
        model.addAttribute("page", "Viv Day Spa :: Your Personalized Menu");

        return "menu";
    }
}
