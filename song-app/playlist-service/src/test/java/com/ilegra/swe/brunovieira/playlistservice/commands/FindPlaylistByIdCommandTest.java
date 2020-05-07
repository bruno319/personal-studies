package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindPlaylistByIdCommandTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private Playlist playlist;

    @Test
    public void shouldReturnPlaylistGivenItsId() throws Exception {
        final String id = "id";
        when(playlistRepository.findById(eq(id))).thenReturn(Optional.of(playlist));

        assertEquals(playlist, new FindPlaylistByIdCommand(id, playlistRepository).run());
    }

    @Test
    public void fallbackShouldReturnAnyPlaylist() {
        assertNotNull(new FindPlaylistByIdCommand("id", playlistRepository).getFallback());
    }
}
