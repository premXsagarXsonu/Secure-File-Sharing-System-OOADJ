package com.filesharingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.filesharingsystem.model.Client;
import com.filesharingsystem.model.Peer;
import com.filesharingsystem.model.UserFactory;
import com.filesharingsystem.model.Room;
import com.filesharingsystem.service.DBService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//@RestController
@CrossOrigin("*")
@Controller

public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    UserFactory f = new UserFactory();
    Room room;
    Client client;
    Peer peer;
    boolean isClient;
    // REST API ports(connection ports)
    String mainPort;
    String mainIP;
    // For data ports: client.getIP()/getPort()

    // For clients connection port/IP:
    String clink;
    // Crypto c;
    @Autowired
    private DBService fileService;

    @PostMapping("/createRoom")
    public String createRoomFunc(HttpServletRequest request, Model model, String nickname)
            throws JsonProcessingException, IOException, InterruptedException {
        fileService.deleteAll();
        isClient = true;
        clink = request.getRequestURL().toString().split("/")[0] + "//"
                + request.getRequestURL().toString().split("/")[2];
        client = (Client) f.getObject(2, nickname, clink);
        client = new Client(nickname, clink);
        room = client.getRoom();
        peer = null;
        mainIP = request.getLocalName();
        mainPort = "" + request.getLocalPort();
        // c = new Crypto(room.getKey());
        room.addPeers(mainIP + ":" + mainPort);

        return "dashboard";
    }

    @PostMapping("/joinRoom")
    public String joinRoomFunc(HttpServletRequest request, Model model, String nickname, String link)
            throws IOException, InterruptedException {
        fileService.deleteAll();
        clink = link;
        isClient = false;
        room = null;
        peer = (Peer) f.getObject(1, nickname, null);
        // peer = new (nickname);
        client = null;
        mainIP = request.getLocalName();
        mainPort = "" + request.getLocalPort();

        return "dashboard";
    }

    @GetMapping("/getFiles")
    public ResponseEntity<?> getFilesRoute() throws IOException {
        return new ResponseEntity<>(fileService.getFiles().toString(), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws IOException {
        fileService.deleteFile(id);
        return "dashboard";
    }

    @GetMapping("/showAllFiles")
    public String showAllFiles() {
        return "dashboard";
    }

    @PostMapping("/showAllFiles")
    public String showAllFilesPost() {
        return "dashboard";
    }
}
