package com.ilegra.swe.brunovieira.appservice.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FallbackHandlerTest {

    @Test
    public void shouldReturnSongFeignRestFallback() {
        SongFeignRest songRestFallback = FallbackHandler.getSongRestFallback();
        assertNotNull(songRestFallback);
    }

    @Test
    public void shouldReturnPlaylistFeignRestFallback() {
        PlaylistFeignRest playlistRestFallback = FallbackHandler.getPlaylistRestFallback();
        assertNotNull(playlistRestFallback);
    }
}
