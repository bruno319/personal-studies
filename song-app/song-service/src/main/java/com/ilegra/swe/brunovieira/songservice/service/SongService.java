package com.ilegra.swe.brunovieira.songservice.service;

import com.ilegra.swe.brunovieira.songservice.commands.FindAllSongsCommand;
import com.ilegra.swe.brunovieira.songservice.commands.FindSongByIdCommand;
import com.ilegra.swe.brunovieira.songservice.commands.SaveSongCommand;
import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song findSongById(String id) {
        return new FindSongByIdCommand(id, songRepository)
                .execute();
    }

    public List<Song> findAllSongs() {
        return new FindAllSongsCommand(songRepository)
                .execute();
    }

    public Boolean save(Song song) {
        return new SaveSongCommand(song, songRepository)
                .execute();
    }
}
