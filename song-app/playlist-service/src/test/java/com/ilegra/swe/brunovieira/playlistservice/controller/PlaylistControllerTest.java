package com.ilegra.swe.brunovieira.playlistservice.controller;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.service.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTest {

    @MockBean
    private PlaylistService playlistService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreatePlaylist() throws Exception {
        final String playlistName = "MyPlaylist";
        when(playlistService.createPlaylist(eq(playlistName))).thenReturn(true);

        mockMvc.perform(post("/playlists")
                .param("name", playlistName))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailOnCreatingPlaylist() throws Exception {
        when(playlistService.createPlaylist(anyString())).thenReturn(false);

        mockMvc.perform(post("/playlists")
                .param("name", "MyPlaylist"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnPlaylistGivenItsId() throws Exception {
        final String id = "id";
        when(playlistService.findPlaylistById(eq(id))).thenReturn(new Playlist("MyPlaylist"));

        mockMvc.perform(get("/playlists/id"))
                .andExpect(jsonPath("$.name", is("MyPlaylist")));
    }

    @Test
    public void shouldReturnNotFoundIfIdIsInvalid() throws Exception {
        when(playlistService.findPlaylistById(anyString())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/playlists/id"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAllPlaylists() throws Exception {
        List<Playlist> playlists = Arrays.asList(
                new Playlist("MyPlaylist"), new Playlist("OtherPlaylist"));
        when(playlistService.findAllPlaylists()).thenReturn(playlists);

        mockMvc.perform(get("/playlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("MyPlaylist")))
                .andExpect(jsonPath("$[1].name", is("OtherPlaylist")));
    }

    @Test
    public void shouldAddSongToPlaylist() throws Exception {
        final String playlistId = "playlistId";
        final String songId = "songId";
        when(playlistService.addSongToPlaylist(eq(playlistId), eq(songId))).thenReturn(true);

        mockMvc.perform(post("/playlists/" + playlistId)
                .param("songId", songId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundIfIdIsInvalidOnAddingSongs() throws Exception {
        doThrow(IllegalArgumentException.class).when(playlistService).addSongToPlaylist(anyString(), anyString());

        mockMvc.perform(post("/playlists/id")
                .param("songId", "songId"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteSongInPlaylist() throws Exception {
        final String playlistId = "playlistId";
        final String songId = "songId";
        when(playlistService.removeSongInPlaylist(playlistId, songId)).thenReturn(true);

        mockMvc.perform(delete("/playlists/" + playlistId)
                .param("songId", songId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestIfPlaylistDoesNotHaveSongId() throws Exception {
        when(playlistService.removeSongInPlaylist(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(delete("/playlists/id")
                .param("songId", "songId"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNotFoundIfIdIsInvalidOnDeletingSongs() throws Exception {
        when(playlistService.removeSongInPlaylist(anyString(), anyString())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(delete("/playlists/id")
                .param("songId", "songId"))
                .andExpect(status().isNotFound());
    }
}
