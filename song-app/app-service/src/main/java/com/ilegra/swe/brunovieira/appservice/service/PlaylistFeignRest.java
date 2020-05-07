package com.ilegra.swe.brunovieira.appservice.service;

import com.ilegra.swe.brunovieira.appservice.domain.Playlist;
import feign.Param;
import feign.RequestLine;

public interface PlaylistFeignRest {

    @RequestLine("GET /playlists/{id}")
    Playlist fetchPlaylist(@Param("id") String id);
}
