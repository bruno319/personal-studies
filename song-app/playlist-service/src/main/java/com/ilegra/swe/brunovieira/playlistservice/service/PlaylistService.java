package com.ilegra.swe.brunovieira.playlistservice.service;

import com.ilegra.swe.brunovieira.playlistservice.commands.FindAllPlaylistsCommand;
import com.ilegra.swe.brunovieira.playlistservice.commands.FindPlaylistByIdCommand;
import com.ilegra.swe.brunovieira.playlistservice.commands.SavePlaylistCommand;
import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist findPlaylistById(String id) {
        return new FindPlaylistByIdCommand(id, playlistRepository)
                .execute();
    }

    public List<Playlist> findAllPlaylists() {
        return new FindAllPlaylistsCommand(playlistRepository)
                .execute();
    }

    public Boolean addSongToPlaylist(String playlistId, String songId) {
        Playlist playlist = new FindPlaylistByIdCommand(playlistId, playlistRepository)
                .execute();
        playlist.addSong(songId);
        return new SavePlaylistCommand(playlist, playlistRepository).execute();
    }

    public boolean removeSongInPlaylist(String playlistId, String songId) {
        Playlist playlist = new FindPlaylistByIdCommand(playlistId, playlistRepository)
                .execute();
        boolean removed = playlist.removeSong(songId);
        if (removed) removed = new SavePlaylistCommand(playlist, playlistRepository).execute();
        return removed;
    }

    public Boolean createPlaylist(String name) {
        return new SavePlaylistCommand(new Playlist(name), playlistRepository)
                .execute();
    }
}
