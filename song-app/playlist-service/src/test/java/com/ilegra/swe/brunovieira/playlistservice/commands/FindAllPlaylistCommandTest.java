package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindAllPlaylistCommandTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Test
    public void shouldReturnAllPlaylistInRepository() throws Exception {
        ArrayList playlists = mock(ArrayList.class);
        when(playlistRepository.findAll()).thenReturn(playlists);

        assertEquals(playlists, new FindAllPlaylistsCommand(playlistRepository).run());
    }

    @Test
    public void fallbackShouldReturnAnEmptyList() {
        assertTrue(new FindAllPlaylistsCommand(playlistRepository).getFallback().isEmpty());
    }
}
