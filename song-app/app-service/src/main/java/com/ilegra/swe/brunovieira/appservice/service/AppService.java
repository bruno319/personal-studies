package com.ilegra.swe.brunovieira.appservice.service;

import com.ilegra.swe.brunovieira.appservice.domain.Playlist;
import com.ilegra.swe.brunovieira.appservice.domain.Song;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppService {

    private LoadBalancerManager loadBalancerManager;

    @Autowired
    public AppService(LoadBalancerManager loadBalancerManager) {
        this.loadBalancerManager = loadBalancerManager;
    }

    public List<Song> listSongs(String id) {
        return fetchPlaylist(id)
                .getSongs()
                .stream()
                .map(this::fetchSong)
                .collect(Collectors.toList());
    }

    private Playlist fetchPlaylist(String id) {
        Optional<ILoadBalancer> loadBalancer = loadBalancerManager.getLoadBalancer(ServicesEnum.PLAYLIST_SERVICE);

        if (!loadBalancer.isPresent()) {
            return FallbackHandler.getPlaylistRestFallback().fetchPlaylist("null");
        }

        return LoadBalancerCommand.<Playlist>builder()
                .withLoadBalancer(loadBalancer.get())
                .build()
                .submit(server -> {
                    PlaylistFeignRest playlistRest = HystrixFeign.builder()
                            .encoder(new GsonEncoder())
                            .decoder(new GsonDecoder())
                            .target(
                                    PlaylistFeignRest.class,
                                    "http://" + server.getHostPort(),
                                    FallbackHandler.getPlaylistRestFallback()
                            );
                    return Observable.just(playlistRest.fetchPlaylist(id));
                })
                .toBlocking()
                .first();
    }

    private Song fetchSong(String id) {
        Optional<ILoadBalancer> loadBalancer = loadBalancerManager.getLoadBalancer(ServicesEnum.SONG_SERVICE);

        if (!loadBalancer.isPresent()) {
            return FallbackHandler.getSongRestFallback().fetchSong("null");
        }

        return LoadBalancerCommand.<Song>builder()
                .withLoadBalancer(loadBalancer.get())
                .build()
                .submit(server -> {
                    SongFeignRest songRest = HystrixFeign.builder()
                            .encoder(new GsonEncoder())
                            .decoder(new GsonDecoder())
                            .target(
                                    SongFeignRest.class,
                                    "http://" + server.getHostPort(),
                                    FallbackHandler.getSongRestFallback()
                            );
                    return Observable.just(songRest.fetchSong(id));
                })
                .toBlocking()
                .first();
    }
}
