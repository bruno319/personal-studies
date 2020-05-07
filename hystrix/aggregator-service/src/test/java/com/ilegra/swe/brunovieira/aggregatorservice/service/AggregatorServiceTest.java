package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.TweetsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AggregatorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AggregatorService aggregatorService;

    @Mock
    private TweetsDto tweetsDto;

    @Mock
    private GithubUserDto githubUserDto;

    @Test
    public void shouldReturnNumberOfRepositories() {
        when(restTemplate.getForObject(
                eq("http://localhost:8282/github/GitUser123"),
                same(GithubUserDto.class)
        )).thenReturn(githubUserDto);
        when(githubUserDto.getPublicRepositories()).thenReturn(15);

        assertEquals(15, aggregatorService.getNumberRepositories("GitUser123"));
    }

    @Test
    public void shouldReturnNumberOfTweets() {
        when(restTemplate.getForObject(
                eq("http://localhost:8181/tweets/TwitterUser123"),
                same(TweetsDto.class)
        )).thenReturn(tweetsDto);
        when(tweetsDto.getTweets()).thenReturn(10);

        assertEquals(10, aggregatorService.getNumberTweets("TwitterUser123"));
    }
}
