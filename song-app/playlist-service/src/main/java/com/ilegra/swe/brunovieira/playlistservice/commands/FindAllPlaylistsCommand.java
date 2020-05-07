package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Collections;
import java.util.List;

public class FindAllPlaylistsCommand extends HystrixCommand<List<Playlist>> {

    private PlaylistRepository repository;

    public FindAllPlaylistsCommand(PlaylistRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("FindAllPlaylistGroup"));
        this.repository = repository;
    }

    @Override
    protected List<Playlist> run() throws Exception {
        return repository.findAll();
    }

    @Override
    protected List<Playlist> getFallback() {
        return Collections.emptyList();
    }
}
