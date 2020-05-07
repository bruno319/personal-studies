package com.ilegra.swe.brunovieira.songservice.commands;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import com.ilegra.swe.brunovieira.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class SaveSongCommand extends HystrixCommand<Boolean> {

    private Song song;
    private SongRepository repository;

    public SaveSongCommand(Song song, SongRepository repository) {
        super(HystrixCommandGroupKey.Factory.asKey("SaveSongGroup"));
        this.song = song;
        this.repository = repository;
    }

    @Override
    public Boolean run() throws Exception {
        repository.save(song);
        return true;
    }

    @Override
    public Boolean getFallback() {
        return false;
    }
}
