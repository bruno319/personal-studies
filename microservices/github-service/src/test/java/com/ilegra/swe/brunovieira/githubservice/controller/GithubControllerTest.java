package com.ilegra.swe.brunovieira.githubservice.controller;

import com.ilegra.swe.brunovieira.githubservice.dto.GithubUserDto;
import com.ilegra.swe.brunovieira.githubservice.service.GithubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GithubController.class)
public class GithubControllerTest {

    @MockBean
    private GithubService githubService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNumberOfGithubRepositoriesGivenAnUser() throws Exception {
        GithubUserDto githubUserDto = new GithubUserDto();
        githubUserDto.setPublicRepositories(15);
        when(githubService.getNumberRepository("User123")).thenReturn(githubUserDto);

        mockMvc.perform(get("/github/User123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.publicRepositories").value(15));
    }

    @Test
    public void shouldReturnStatus404IfHttpExceptionIsThrown() throws Exception {
        when(githubService.getNumberRepository("User123")).thenThrow(HttpClientErrorException.class);

        mockMvc.perform(get("/github/User123"))
                .andExpect(status().isNotFound());
    }
}
