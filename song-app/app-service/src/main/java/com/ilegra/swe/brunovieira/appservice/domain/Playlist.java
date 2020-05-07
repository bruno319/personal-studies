package com.ilegra.swe.brunovieira.appservice.domain;

import java.util.List;

public class Playlist {

    private String id;
    private String name;
    private List<String> songs;

    public Playlist(String id, String name, List<String> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSongs() {
        return songs;
    }
}
