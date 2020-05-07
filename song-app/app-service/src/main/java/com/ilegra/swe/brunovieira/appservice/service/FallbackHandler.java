package com.ilegra.swe.brunovieira.appservice.service;

import com.ilegra.swe.brunovieira.appservice.domain.Playlist;
import com.ilegra.swe.brunovieira.appservice.domain.Song;

import java.util.Collections;

public class FallbackHandler {

    static PlaylistFeignRest getPlaylistRestFallback() {
        return (id) -> new Playlist("null", "Playlist fallback", Collections.singletonList("someId"));
    }

    static SongFeignRest getSongRestFallback() {
        return (id) -> new Song("null", "Song fallback");
    }
}
