package com.ilegra.swe.brunovieira.aggregatorservice.controller;

import com.ilegra.swe.brunovieira.aggregatorservice.service.AggregatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AggregatorController.class)
public class AggregatorControllerTest {

    @MockBean
    private AggregatorService aggregatorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnInformation() throws Exception {
        String githubUser = "GithubUser123";
        String twitterUser = "TwitterUser123";
        when(aggregatorService.getNumberRepositories(eq(githubUser))).thenReturn(15);
        when(aggregatorService.getNumberTweets(eq(twitterUser))).thenReturn(12);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("githubUsername", "GithubUser123");
        params.add("twitterScreenName", "TwitterUser123");
        mockMvc.perform(get("/aggregator")
                .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.githubUsername").value(githubUser))
                .andExpect(jsonPath("$.twitterScreenName").value(twitterUser))
                .andExpect(jsonPath("$.repositories").value(15))
                .andExpect(jsonPath("$.tweets").value(12));
    }
}
