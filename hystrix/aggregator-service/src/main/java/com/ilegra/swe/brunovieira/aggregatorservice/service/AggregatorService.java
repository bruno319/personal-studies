package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.TweetsDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatorService {

    private RestTemplate restTemplate;

    @Autowired
    public AggregatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallbackValue", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")})
    public int getNumberTweets(String twitterScreenName) {
        return restTemplate.getForObject(
                "http://localhost:8181/tweets/" + twitterScreenName, TweetsDto.class)
                .getTweets();
    }

    @HystrixCommand(fallbackMethod = "fallbackValue")
    public int getNumberRepositories(String githubUsername) {
        return restTemplate.getForObject(
                "http://localhost:8282/github/" + githubUsername, GithubUserDto.class)
                .getPublicRepositories();
    }

    @SuppressWarnings("unused")
    private int fallbackValue(String username) {
        return -1;
    }
}
