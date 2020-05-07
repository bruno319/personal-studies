package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FindSongByIdCommand extends HystrixCommand<Song> {

    private SongRepository repository;
    private String id;

    public FindSongByIdCommand(String id, SongRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("FindSongByIdGroup"));
        this.repository = repository;
        this.id = id;
    }

    @Override
    protected Song run() throws Exception {
        return repository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    protected Song getFallback() {
        return new Song("Song returned from fallback");
    }
}
