package com.ilegra.swe.brunovieira.songservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.service.SongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTest {

    @MockBean
    private SongService songService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveSong() throws Exception {
        when(songService.save(any(Song.class))).thenReturn(true);

        mockMvc.perform(post("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(new Song())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFailOnSavingSong() throws Exception {
        when(songService.save(any(Song.class))).thenReturn(false);

        mockMvc.perform(post("/songs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(new Song())))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnAllSongs() throws Exception {
        List<Song> songs = Arrays.asList(new Song("Hallowed Be Thy Name"), new Song("Paradise City"));
        when(songService.findAllSongs()).thenReturn(songs);

        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Hallowed Be Thy Name")))
                .andExpect(jsonPath("$[1].name", is("Paradise City")));
    }

    @Test
    public void shouldReturnSongGivenItsId() throws Exception {
        when(songService.findSongById(eq("id"))).thenReturn(new Song("Hallowed Be Thy Name"));

        mockMvc.perform(get("/songs/id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Hallowed Be Thy Name")));
    }

    @Test
    public void shouldReturnNotFoundIfIdIsInvalid() throws Exception {
        when(songService.findSongById(any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/songs/id"))
                .andExpect(status().isNotFound());
    }
}
