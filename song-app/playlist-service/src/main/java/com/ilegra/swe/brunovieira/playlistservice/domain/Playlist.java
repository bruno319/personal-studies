package com.ilegra.swe.brunovieira.playlistservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document
public class Playlist {

    @Id
    private String id;

    private String name;

    private List<String> songs;
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(String id) {
        songs.add(id);
    }

    public boolean removeSong(String id) {
        return songs.remove(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSongs() {
        return Collections.unmodifiableList(songs);
    }
}
