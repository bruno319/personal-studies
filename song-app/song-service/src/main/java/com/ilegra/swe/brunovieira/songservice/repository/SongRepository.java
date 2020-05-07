package com.ilegra.swe.brunovieira.songservice.repository;

import com.ilegra.swe.brunovieira.songservice.domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {


}
