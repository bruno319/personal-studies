package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.InfoDto;
import com.ilegra.swe.brunovieira.aggregatorservice.dto.TweetsDto;
import com.ilegra.swe.brunovieira.aggregatorservice.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AggregatorServiceTest {

    @Mock
    private RequestUserService requestUserService;

    @InjectMocks
    private AggregatorService aggregatorService;

    @Mock
    TweetsDto tweetsDto;

    @Mock
    GithubUserDto githubUserDto;

    @Test
    public void shouldReturnUserInformation() throws UserNotFoundException {
        when(requestUserService.doRequest(
                eq("http://localhost:8181/tweets/TwitterUser123"),
                same(TweetsDto.class)
        )).thenReturn(tweetsDto);
        when(requestUserService.doRequest(
                eq("http://localhost:8282/github/GitUser123"),
                same(GithubUserDto.class)
        )).thenReturn(githubUserDto);
        when(tweetsDto.getTweets()).thenReturn(10);
        when(githubUserDto.getPublicRepositories()).thenReturn(15);

        InfoDto userInformation = aggregatorService.getUserInformation("GitUser123", "TwitterUser123");

        assertEquals("TwitterUser123", userInformation.getTwitterScreenName());
        assertEquals("GitUser123", userInformation.getGithubUsername());
        assertEquals(10, userInformation.getTweets());
        assertEquals(15, userInformation.getRepositories());
    }

    @Test
    public void shouldReturnUserInformationWithInvalidTwitterUser() throws UserNotFoundException {
        when(requestUserService.doRequest(
                eq("http://localhost:8181/tweets/TwitterUser123"),
                same(TweetsDto.class)
        )).thenThrow(UserNotFoundException.class);
        when(requestUserService.doRequest(
                eq("http://localhost:8282/github/GitUser123"),
                same(GithubUserDto.class)
        )).thenReturn(githubUserDto);
        when(githubUserDto.getPublicRepositories()).thenReturn(15);

        InfoDto userInformation = aggregatorService.getUserInformation("GitUser123", "TwitterUser123");

        assertEquals("User not found", userInformation.getTwitterScreenName());
        assertEquals("GitUser123", userInformation.getGithubUsername());
        assertEquals(-1, userInformation.getTweets());
        assertEquals(15, userInformation.getRepositories());
    }

    @Test
    public void shouldReturnUserInformationWithInvalidGithubUser() throws UserNotFoundException {
        when(requestUserService.doRequest(
                eq("http://localhost:8181/tweets/TwitterUser123"),
                same(TweetsDto.class)
        )).thenReturn(tweetsDto);
        when(requestUserService.doRequest(
                eq("http://localhost:8282/github/GitUser123"),
                same(GithubUserDto.class)
        )).thenThrow(UserNotFoundException.class);
        when(tweetsDto.getTweets()).thenReturn(10);

        InfoDto userInformation = aggregatorService.getUserInformation("GitUser123", "TwitterUser123");

        assertEquals("TwitterUser123", userInformation.getTwitterScreenName());
        assertEquals("User not found", userInformation.getGithubUsername());
        assertEquals(10, userInformation.getTweets());
        assertEquals(-1, userInformation.getRepositories());
    }
}
