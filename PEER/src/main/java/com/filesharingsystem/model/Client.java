package com.filesharingsystem.model;

import java.io.File;
import java.util.ArrayList;

public class Client {
    String IP;
    String port;
    private String nickname;
    private Room room;

    public Client(String nickname_, String link) {
        int min = 5000;
        int max = 9000;
        int port_ = (int) (Math.random() * (max - min + 1) + min);
        port = Integer.toString(port_);
        IP = "127.0.0.1";
        nickname = nickname_;
        room = createRoom(nickname_, link);

    }

    private Room createRoom(String nickname, String link_) {
        Room room_;
        if (nickname == null)
            room_ = null;
        else
            room_ = new Room(nickname, link_);

        return room_;
    }

    public String getIP() {
        return IP;
    }

    public String getPort() {
        return port;
    }

    public Room getRoom() {
        return room;
    }

    public String getNickname() {
        return nickname;
    }

}