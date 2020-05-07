package com.ilegra.swe.brunovieira.songservice.controller;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable String id) {
        try {
            Song song = songService.findSongById(id);
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        List<Song> songs = songService.findAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveSong(@RequestBody Song song) {
        Boolean persisted = songService.save(song);
        if (persisted) return new ResponseEntity(HttpStatus.CREATED);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not possible save the song");
    }
}
