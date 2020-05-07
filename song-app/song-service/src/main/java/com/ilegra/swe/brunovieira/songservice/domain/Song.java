package com.ilegra.swe.brunovieira.songservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Song {

    @Id
    private String id;
    private String name;

    public Song() {
    }

    public Song(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
