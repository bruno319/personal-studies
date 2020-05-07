package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SavePlaylistCommandTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private Playlist playlist;

    @Test
    public void shouldSavePlaylistInRepository() throws Exception {
        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);

        new SavePlaylistCommand(playlist, playlistRepository).run();
        verify(playlistRepository, times(1)).save(playlist);
    }

    @Test
    public void fallbackShouldReturnFalse() {
        assertFalse(new SavePlaylistCommand(playlist, playlistRepository).getFallback());
    }
}
