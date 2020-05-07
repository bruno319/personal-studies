package com.ilegra.swe.brunovieira.aggregatorservice.controller;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.InfoDto;
import com.ilegra.swe.brunovieira.aggregatorservice.service.AggregatorService;
import com.ilegra.swe.brunovieira.aggregatorservice.service.RequestUserService;
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
import static org.mockito.Mockito.validateMockitoUsage;
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
        InfoDto infoDto = createInfoDto();

        when(aggregatorService.getUserInformation(
                eq("GithubUser123"),
                eq("TwitterUser123")
        )).thenReturn(infoDto);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("githubUsername", "GithubUser123");
        params.add("twitterScreenName", "TwitterUser123");
        mockMvc.perform(get("/aggregator")
                .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.githubUsername").value(infoDto.getGithubUsername()))
                .andExpect(jsonPath("$.twitterScreenName").value(infoDto.getTwitterScreenName()))
                .andExpect(jsonPath("$.tweets").value(infoDto.getTweets()))
                .andExpect(jsonPath("$.repositories").value(infoDto.getRepositories()));
    }

    private InfoDto createInfoDto() {
        InfoDto infoDto = new InfoDto();
        infoDto.setGithubUsername("GithubUser123");
        infoDto.setTwitterScreenName("TwitterUser123");
        infoDto.setTweets(10);
        infoDto.setRepositories(15);

        return infoDto;
    }
}
