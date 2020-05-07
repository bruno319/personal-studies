package com.ilegra.swe.brunovieira.twitterservice.service;

import com.ilegra.swe.brunovieira.twitterservice.dto.TweetsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TweetService {

    private CustomTwitterFactory twitterFactory;

    @Autowired
    public TweetService(CustomTwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
    }

    public TweetsDto getNumberTweets(String screenName) throws TwitterException {
        Twitter twitter = twitterFactory.getTwitter();
        TweetsDto tweetsDto = new TweetsDto();

        int totalTweets = 0;
        int pageNumber = 1;
        while (true) {
            int currentTweetsSize = totalTweets;
            Paging page = new Paging(pageNumber++, 100);
            ResponseList<Status> timeline = twitter.getUserTimeline(screenName, page);
            totalTweets += timeline.size();
            if (currentTweetsSize == totalTweets) break;
        }

        tweetsDto.setTweets(totalTweets);
        return tweetsDto;
    }
}
