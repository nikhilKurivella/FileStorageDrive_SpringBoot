package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication authentication, Note note){
        String username = authentication.getName();
        User user = userService.getUser(username);
        note.setUserid(user.getUserid());

        int rowAdded = 0;
        if(note.getNoteid() == null) rowAdded = noteService.createNote(note);
        else rowAdded = noteService.updateNote(note);

        if (rowAdded > 0) return "redirect:/result?success";
        else if (rowAdded == 0) return "redirect:/result?bigNote";
        else return "redirect:/result?error";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("noteid") Integer noteid){
        if(noteid != null){
            this.noteService.deleteNote(noteid);
            return "redirect:/result?success";
        }
        else return "redirect:/result?error";

    }
}
