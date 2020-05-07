package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class SavePlaylistCommand extends HystrixCommand<Boolean> {

    private final Playlist playlist;
    private final PlaylistRepository repository;

    public SavePlaylistCommand(Playlist playlist, PlaylistRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("SavePlaylistGroup"));
        this.playlist = playlist;
        this.repository = repository;
    }

    @Override
    protected Boolean run() throws Exception {
        repository.save(playlist);
        return true;
    }

    @Override
    protected Boolean getFallback() {
        return false;
    }
}
