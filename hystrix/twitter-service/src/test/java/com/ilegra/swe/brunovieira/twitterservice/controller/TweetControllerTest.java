package com.ilegra.swe.brunovieira.twitterservice.controller;

import com.ilegra.swe.brunovieira.twitterservice.dto.TweetsDto;
import com.ilegra.swe.brunovieira.twitterservice.service.CustomTwitterFactory;
import com.ilegra.swe.brunovieira.twitterservice.service.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TweetController.class)
public class TweetControllerTest {

    @MockBean
    private TweetService tweetService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNumberOfTweetsGivenAnUser() throws Exception {
        TweetsDto tweetsDto = new TweetsDto();
        tweetsDto.setTweets(15);
        when(tweetService.getNumberTweets("User123")).thenReturn(tweetsDto);

        mockMvc.perform(get("/tweets/User123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tweets").value(15));
    }
}
