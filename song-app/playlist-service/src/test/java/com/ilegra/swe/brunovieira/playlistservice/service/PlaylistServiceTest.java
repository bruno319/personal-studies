package com.ilegra.swe.brunovieira.playlistservice.service;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PlaylistServiceTest {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @Before
    public void dropDatabase() {
        playlistRepository.deleteAll();
    }

    @Test
    public void shouldCreatePlaylist() {
        playlistService.createPlaylist("MyPlaylist");

        assertEquals(1, playlistRepository.count());
        assertEquals("MyPlaylist", playlistRepository.findAll().get(0).getName());
    }

    @Test
    public void shouldFetchAllPlaylists() {
        playlistRepository.save(new Playlist("MyPlaylist"));
        playlistRepository.save(new Playlist("MyPlaylist2"));

        assertEquals(2, playlistService.findAllPlaylists().size());
    }

    @Test
    public void shouldFindPlaylistById() {
        playlistRepository.save(new Playlist("MyPlaylist"));
        String id = playlistRepository.findAll().get(0).getId();

        Playlist playlist = playlistService.findPlaylistById(id);
        assertNotNull(playlist);
        assertEquals("MyPlaylist", playlist.getName());
    }

    @Test
    public void shouldAddSongToPlaylist() {
        playlistRepository.save(new Playlist("MyPlaylist"));
        String id = playlistRepository.findAll().get(0).getId();

        Boolean saved = playlistService.addSongToPlaylist(id, "songID");
        assertTrue(saved);
        assertEquals(1, playlistRepository.findAll().get(0).getSongs().size());
        assertEquals("songID", playlistRepository.findAll().get(0).getSongs().get(0));
    }

    @Test
    public void shouldDeleteSongInPlaylist() {
        Playlist playlist = new Playlist("MyPlaylist");
        playlist.addSong("songID");
        playlistRepository.save(playlist);
        String id = playlistRepository.findAll().get(0).getId();

        boolean removed = playlistService.removeSongInPlaylist(id, "songID");

        assertTrue(removed);
        assertTrue(playlistRepository.findAll().get(0).getSongs().isEmpty());


    }
}
