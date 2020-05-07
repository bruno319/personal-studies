package com.ilegra.swe.brunovieira.appservice.service;

import com.ilegra.swe.brunovieira.appservice.domain.Song;
import feign.Param;
import feign.RequestLine;

public interface SongFeignRest {

    @RequestLine("GET /songs/{id}")
    Song fetchSong(@Param("id") String id);
}
