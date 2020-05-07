package com.ilegra.swe.brunovieira.aggregatorservice.controller;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.InfoDto;
import com.ilegra.swe.brunovieira.aggregatorservice.service.AggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregator")
public class AggregatorController {

    private AggregatorService aggregatorService;

    @Autowired
    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping
    public ResponseEntity<InfoDto> getUserInfo(
            @RequestParam("githubUsername") String githubUsername,
            @RequestParam("twitterScreenName") String twitterScreenName
    ) {
        int tweets = aggregatorService.getNumberTweets(twitterScreenName);
        int repositories = aggregatorService.getNumberRepositories(githubUsername);

        InfoDto infoDto = new InfoDto();
        infoDto.setGithubUsername(githubUsername);
        infoDto.setTwitterScreenName(twitterScreenName);
        infoDto.setRepositories(repositories);
        infoDto.setTweets(tweets);
        return new ResponseEntity<>(infoDto, HttpStatus.OK);
    }
}
