package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){

        String signupError = null;

        if(!userService.isUsernameAvailable(user.getUsername())){
            signupError = "Username is already taken. Please choose another username.";
            model.addAttribute("signupError", signupError);
        }

        if (signupError==null){
            int rowsAdded = userService.createUser(user);
            if(rowsAdded<0){
                signupError = "An error occured. Please try again.";
                model.addAttribute("signupError", signupError);
            }
            else{
                model.addAttribute("signupSuccess",true);
                return "login";
            }
        }
        return "signup";
    }
}
