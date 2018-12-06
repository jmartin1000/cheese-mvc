package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        cheeses.put(cheeseName, cheeseDescription);

        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String displayEditCheeseForm(Model model){

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Edit List");

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditCheeseForm(@RequestParam ArrayList<String> cheese) {
        // testing to see what parameters the controller is pulling out
        System.out.println(cheese);

        for (String name : cheese) {
            cheeses.remove(name);
        }

        // Redirect to /cheese
        return "redirect:";
    }
}
