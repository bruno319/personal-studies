package com.ilegra.swe.brunovieira.songservice.service;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SongServiceTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @After
    public void dropDatabase() {
        songRepository.deleteAll();
    }

    @Test
    public void shouldSaveSong() {
        songService.save(new Song("Hallowed Be Thy Name"));

        assertEquals(1, songRepository.findAll().size());
    }

    @Test
    public void shouldFetchAllSongs() {
        songRepository.save(new Song("Hallowed Be Thy Name"));
        songRepository.save(new Song("Paradise City"));

        assertEquals(2, songService.findAllSongs().size());
    }

    @Test
    public void shouldFindSongById() {
        songRepository.save(new Song("Hallowed Be Thy Name"));
        String id = songRepository.findAll().get(0).getId();

        Song song = songService.findSongById(id);
        assertNotNull(song);
        assertEquals("Hallowed Be Thy Name", song.getName());
    }
}
