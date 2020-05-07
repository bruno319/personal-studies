package com.ilegra.swe.brunovieira.playlistservice.controller;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity createPlaylist(@RequestParam String name) {
        Boolean persisted = playlistService.createPlaylist(name);
        if (persisted) return new ResponseEntity<>(HttpStatus.CREATED);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create the playlist");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable String id) {
        try {
            Playlist playlist = playlistService.findPlaylistById(id);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlists = playlistService.findAllPlaylists();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/{playlistId}")
    public ResponseEntity saveSongInPlaylist(
            @PathVariable String playlistId, @RequestParam String songId) {
        try {
            playlistService.addSongToPlaylist(playlistId, songId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found");
        }
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity deleteSongInPlaylist(
            @PathVariable String playlistId, @RequestParam String songId) {
        try {
            boolean removed = playlistService.removeSongInPlaylist(playlistId, songId);
            if (removed) return new ResponseEntity<>(HttpStatus.OK);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playlist does not contains this song");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found");
        }
    }
}
