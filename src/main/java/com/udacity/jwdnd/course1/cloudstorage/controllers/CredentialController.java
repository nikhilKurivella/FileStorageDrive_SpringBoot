package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/credentials")
    public String createOrUpdateCredential(Authentication authentication, Credential credential){
        String username = authentication.getName();
        User user = userService.getUser(username);
        credential.setUserid(user.getUserid());

        int rowAdded = 0;
        if(credential.getCredentialid() == null) {
//            System.out.println("Inserting Credential");
            rowAdded = credentialService.createCredential(credential);
        }
        else{
//            System.out.println("Updating credential");
            rowAdded = credentialService.updateCredential(credential);
        }

        if (rowAdded > 0) return "redirect:/result?success";
        else return "redirect:/result?error";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("credentialid") Integer credentialid){
        if(credentialid != null){
            this.credentialService.deleteCredential(credentialid);
            return "redirect:/result?success";
        }
        else return "redirect:/result?error";
    }
}
