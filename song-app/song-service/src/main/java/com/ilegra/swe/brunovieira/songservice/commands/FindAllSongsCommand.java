package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Collections;
import java.util.List;

public class FindAllSongsCommand extends HystrixCommand<List<Song>> {

    private SongRepository repository;

    public FindAllSongsCommand(SongRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("FindAllSongsGroup"));
        this.repository = repository;
    }

    @Override
    public List<Song> run() throws Exception {
        return repository.findAll();
    }

    @Override
    protected List<Song> getFallback() {
        return Collections.emptyList();
    }
}
