package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
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
public class FindSongByIdCommandTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private Song song;

    @Test
    public void shouldReturnSongGivenItsId() throws Exception {
        final String id = "id";
        when(songRepository.findById(eq(id))).thenReturn(Optional.of(song));

        assertEquals(new FindSongByIdCommand(id, songRepository).run(), song);
    }

    @Test
    public void fallbackShouldReturnAnySong() {
        assertNotNull(new FindSongByIdCommand("id", songRepository).getFallback());
    }
}
