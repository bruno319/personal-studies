package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveSongCommandTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private Song song;

    @Test
    public void shouldSaveSongInRepository() throws Exception {
        when(songRepository.save(any(Song.class))).thenReturn(song);

        new SaveSongCommand(song, songRepository).run();
        verify(songRepository, times(1)).save(song);
    }

    @Test
    public void fallbackShouldReturnFalse() throws Exception {
        assertFalse(new SaveSongCommand(song, songRepository).getFallback());
    }
}
