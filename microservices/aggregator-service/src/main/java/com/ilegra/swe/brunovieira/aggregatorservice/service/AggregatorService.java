package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.InfoDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.TweetsDto;
import com.ilegra.swe.brunovieira.aggregatorservice.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AggregatorService {

    private RequestUserService requestUserService;

    @Autowired
    public AggregatorService(RequestUserService requestUserService) {
        this.requestUserService = requestUserService;
    }

    public InfoDto getUserInformation(String githubUsername, String twitterScreenName) {
        InfoDto infoDto = new InfoDto();
        infoDto.setGithubUsername(githubUsername);
        infoDto.setTwitterScreenName(twitterScreenName);

        Optional<Integer> tweets = getNumberTweets(twitterScreenName);
        if (!tweets.isPresent()) infoDto.setTwitterScreenName("User not found");

        Optional<Integer> repositories = getNumberRepositories(githubUsername);
        if (!repositories.isPresent()) infoDto.setGithubUsername("User not found");

        infoDto.setRepositories(repositories.orElse(-1));
        infoDto.setTweets(tweets.orElse(-1));

        return infoDto;
    }

    private Optional<Integer> getNumberTweets(String twitterScreenName) {
        try {
            TweetsDto tweetsDto = (TweetsDto) requestUserService
                    .doRequest("http://localhost:8181/tweets/" + twitterScreenName, TweetsDto.class);
            return Optional.of(tweetsDto.getTweets());
        } catch (UserNotFoundException e) {
            return Optional.empty();
        }
    }

    private Optional<Integer> getNumberRepositories(String githubUsername) {
        try {
            GithubUserDto githubUserDto = (GithubUserDto) requestUserService
                    .doRequest("http://localhost:8282/github/" + githubUsername, GithubUserDto.class);
            return Optional.of(githubUserDto.getPublicRepositories());
        } catch (UserNotFoundException e) {
            return Optional.empty();
        }
    }
}
