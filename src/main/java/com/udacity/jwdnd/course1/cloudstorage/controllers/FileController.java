package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/files")
    public String insertFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication) throws IOException {
        if(!fileUpload.isEmpty()) {
            String username = authentication.getName();
            User user = userService.getUser(username);
            int rowAdded = 0;
            try {
                rowAdded = fileService.insertFile(new File(0, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), user.getUserid(), fileUpload.getBytes()));
            }catch(IOException e){
                throw e;
            }
            if (rowAdded > 0) return "redirect:/result?success";
            else if(rowAdded == 0) return "redirect:/result?duplicateFile";
            else return "redirect:/result?error";
        }
        else return "redirect:/result?error";
    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId){
        if(fileId != null){
            this.fileService.deleteFile(fileId);
            return "redirect:/result?success";
        }
        else return "redirect:/result?error";
    }

    @GetMapping("/files/download")
    public ResponseEntity downloadFile (@RequestParam("fileId") Integer fileId, Authentication authentication){
        String username = authentication.getName();
        User user = userService.getUser(username);
        File file = fileService.getFiles(user.getUserid(), fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(  file.getContenttype() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getFiledata()));

    }
}
