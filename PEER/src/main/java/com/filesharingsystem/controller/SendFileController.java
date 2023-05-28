package com.filesharingsystem.controller;

import com.filesharingsystem.model.CustomMultipartFile;
import com.filesharingsystem.service.DBService;
import com.filesharingsystem.service.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.filesharingsystem.model.Room;

@CrossOrigin("*")
@Controller
public class SendFileController {
    Room room;
    private static final Logger logger = LoggerFactory.getLogger(SendFileController.class);

    @Autowired
    private DBService fileService;

    @PostMapping("/sendFile")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        EncryptionService encryptionService = new EncryptionService();
        String id = fileService
                .addFile(new CustomMultipartFile(encryptionService.encryptFile(file.getBytes(), "1k2hplkwsq2h1j21"),
                        file.getOriginalFilename(), file.getContentType(), 0));
        return "dashboard";
    }
}
