package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    // Request path: /user
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "My Cheeses Users");

        return "user/index";
    }

    @RequestMapping(value = "detail/{userId}", method = RequestMethod.GET)
    public String displayDetail(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Register for My Cheeses");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors){

        model.addAttribute("user", user);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Register for My Cheeses");
            return "user/add";
        }

        UserData.add(user);
        return "redirect:";

    }

    private boolean verifyPassword(String password1, String password2){
        return password1.equals(password2);
    }

}
