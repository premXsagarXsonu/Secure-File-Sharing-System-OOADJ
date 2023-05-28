package com.filesharingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Room {

    String link;
    int numUsers;
    String creator;
    List<String> peers;

    String getAlphaNumericString(int n) {
        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public Room(String creator_, String link_) {
        numUsers = 0;
        creator = creator_;
        peers = new ArrayList<String>();

        link = link_;

    }

    public int getNumUsers() {
        return numUsers;
    }

    public List<String> getPeers() {
        return peers;
    }

    public String getClient() {
        return creator;
    }

    public void addPeers(String peer) {
        peers.add(peer);
        numUsers++;
    }

    public String getKey() {
        String keyy = "1k2hjhjwsq2h1j21";
        return keyy;
    }

}
