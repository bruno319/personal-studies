package com.ilegra.swe.brunovieira.githubservice.controller;

import com.ilegra.swe.brunovieira.githubservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.githubservice.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/github")
public class GithubController {

    private GithubService githubService;

    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity getNumberRepository(@PathVariable String username) {
        return new ResponseEntity<>(githubService.getNumberRepository(username), HttpStatus.OK);
    }
}
