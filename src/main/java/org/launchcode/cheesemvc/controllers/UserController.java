package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;



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
    public String displayEditForm(Model model, @PathVariable int userId) {
        model.addAttribute("user", UserData.getById(userId));
        return "user/detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Register for My Cheeses");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify, int id){

        model.addAttribute("user", user);

        if (verifyPassword(user.getPassword(), verify) && verifyUserName(user.getUsername()) && verifyEmail(user.getEmail())){

            UserData.add(user);

            return "redirect:";
        } else {
            if (!verifyUserName(user.getUsername())){
                model.addAttribute("error1", "Remember: letters only for the username. At least 5, no more than 15.");
            }
            if (!verifyEmail(user.getEmail())){
                model.addAttribute("error2", "This is a required field");
            }
            if (!verifyPassword(user.getPassword(), verify)){
                model.addAttribute("error3", "Let's try that password again!");
            }
            return "user/add";
        }
    }

    private boolean verifyPassword(String password1, String password2){
        return password1.equals(password2);
    }

    private boolean verifyUserName(String userName) {

        if (userName.length() < 5 || userName.length() > 15){
            return false;
        }

        char[] chars = userName.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)){
                return false;
            }
        }

        return true;

    }

    private boolean verifyEmail(String email) {
        return email.length() > 0;
    }

}
