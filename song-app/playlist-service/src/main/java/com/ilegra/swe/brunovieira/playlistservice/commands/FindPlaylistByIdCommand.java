package com.ilegra.swe.brunovieira.playlistservice.commands;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import com.ilegra.swe.brunovieira.playlistservice.repository.PlaylistRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FindPlaylistByIdCommand extends HystrixCommand<Playlist> {

    private final String id;
    private final PlaylistRepository repository;

    public FindPlaylistByIdCommand(String id, PlaylistRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("FindPlaylistGroup"));
        this.id = id;
        this.repository = repository;
    }

    @Override
    protected Playlist run() throws Exception {
        return repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    protected Playlist getFallback() {
        return new Playlist("Playlist returned from fallback");
    }
}
