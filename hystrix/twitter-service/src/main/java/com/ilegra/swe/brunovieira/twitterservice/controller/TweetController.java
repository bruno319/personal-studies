package com.ilegra.swe.brunovieira.twitterservice.controller;

import com.ilegra.swe.brunovieira.twitterservice.dto.TweetsDto;
import com.ilegra.swe.brunovieira.twitterservice.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/{screenName}")
    public ResponseEntity getNumberTweets(@PathVariable String screenName) {
        try {
            TweetsDto tweets = tweetService.getNumberTweets(screenName);
            return new ResponseEntity<>(tweets, HttpStatus.OK);
        } catch (TwitterException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
