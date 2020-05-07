package com.ilegra.swe.brunovieira.twitterservice.service;

import com.ilegra.swe.brunovieira.twitterservice.dto.TweetsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {

    @Mock
    private CustomTwitterFactory customTwitterFactory;

    @InjectMocks
    private TweetService tweetService;

    @Mock
    private Twitter twitter;

    @Mock
    private ResponseList<Status> userTimeline;

    @Test
    public void shouldObtainNumberOfTweetsGivenAnUser() throws TwitterException {
        when(customTwitterFactory.getTwitter()).thenReturn(twitter);
        when(twitter.getUserTimeline(anyString(), any(Paging.class))).thenReturn(userTimeline);
        when(userTimeline.size()).thenReturn(100).thenReturn(50).thenReturn(0);

        TweetsDto tweetsDto = tweetService.getNumberTweets("User123");

        assertEquals(150, tweetsDto.getTweets());
        verify(twitter, times(3)).getUserTimeline(anyString(), any(Paging.class));
    }

}
