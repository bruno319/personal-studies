package com.ilegra.swe.brunovieira.appservice.service;

public enum ServicesEnum {

    SONG_SERVICE("SONG-SERVICE"),
    PLAYLIST_SERVICE("PLAYLIST-SERVICE");

    private String serviceName;

    ServicesEnum(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return serviceName;
    }
}
