package com.ilegra.swe.brunovieira.playlistservice.repository;

import com.ilegra.swe.brunovieira.playlistservice.domain.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {
}
