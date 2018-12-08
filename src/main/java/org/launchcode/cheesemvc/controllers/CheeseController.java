package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
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

    static ArrayList<Cheese> cheeses = new ArrayList<>();

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
        Cheese aCheese = new Cheese(cheeseName, cheeseDescription);
        cheeses.add(aCheese);

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
    public String processEditCheeseForm(@RequestParam ArrayList<String> cheeseName) {

        // tried to put the Cheese object as the value of the checkbox input and then pass an
        // arraylist of objects. then simply remove the objects from the arraylist But
        // did not work. why?

        for (String name : cheeseName) {
            for (Cheese cheese : cheeses) {
                if (name.equals(cheese.getName())) {
                    cheeses.remove(cheese);
                    break;
                }
            }
        }

        // Redirect to /cheese
        return "redirect:";
    }
}
