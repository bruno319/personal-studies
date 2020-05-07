package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
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
public class FindAllSongsCommandTest {

    @Mock
    private SongRepository songRepository;

    @Test
    public void shouldReturnAllSongsInRepository() throws Exception {
        ArrayList songs = mock(ArrayList.class);
        when(songRepository.findAll()).thenReturn(songs);

        assertEquals(songs, new FindAllSongsCommand(songRepository).run());
    }

    @Test
    public void fallbackShouldReturnAnEmptyList() {
        assertTrue(new FindAllSongsCommand(songRepository).getFallback().isEmpty());
    }
}
