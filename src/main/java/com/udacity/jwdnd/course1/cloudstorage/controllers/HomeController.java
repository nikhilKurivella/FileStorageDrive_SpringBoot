package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private FileService fileService;
    private EncryptionService encryptionService;
    private CredentialService credentialService;
    private UserService userService;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model){
        String username = authentication.getName();
        User user = this.userService.getUser(username);
        if(user!=null) {
            model.addAttribute("files", this.fileService.getAllFiles(user.getUserid()));
            model.addAttribute("encryptionService", this.encryptionService);
            model.addAttribute("notes", this.noteService.getAllNotes(user.getUserid()));
            model.addAttribute("credentials", this.credentialService.getAllCredentials(user.getUserid()));
            return "home";
        }
        else return "redirect:/login";
    }
}
