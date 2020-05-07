package com.ilegra.swe.brunovieira.appservice.domain;

public class Song {

    private String id;
    private String name;

    public Song(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
