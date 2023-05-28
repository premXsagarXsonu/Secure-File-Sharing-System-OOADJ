package com.filesharingsystem.controller;

import com.filesharingsystem.model.myFile;
import com.filesharingsystem.service.DecryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.filesharingsystem.model.Room;
import com.filesharingsystem.service.DBService;

@Controller
public class ReceiveFileController {
    Room room;
    private static final Logger logger = LoggerFactory.getLogger(ReceiveFileController.class);

    @Autowired
    private DBService fileService;

    @GetMapping("/recieveFile/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws Exception {
        myFile loadFile = fileService.downloadFile(id);
        DecryptionService decryptionService = new DecryptionService();
        byte[] decryptedFile = decryptionService.decryptFile(loadFile.getFile(), "1k2hplkwsq2h1j21");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(decryptedFile));
    }
}
