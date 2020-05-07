package com.ilegra.swe.brunovieira.appservice.controller;

import com.ilegra.swe.brunovieira.appservice.domain.Song;
import com.ilegra.swe.brunovieira.appservice.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/v1")
public class AppController {

    private AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlaylist(@PathVariable String id) {
        List<Song> playlist = appService.listSongs(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }
}
