package com.ilegra.swe.brunovieira.githubservice.service;

import com.ilegra.swe.brunovieira.githubservice.dto.GithubUserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GithubServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private GithubService githubService;

    @Before
    public void setup() {
        githubService = new GithubService(restTemplate, "https://api.github.com/users/");
    }

    @Test
    public void shouldObtainNumberOfGithubRepositoriesGivenAnUser() {
        GithubUserDto githubUserDto = new GithubUserDto();
        when(restTemplate.getForObject(eq("https://api.github.com/users/User123"), same(GithubUserDto.class)))
                .thenReturn(githubUserDto);

        GithubUserDto user123 = githubService.getNumberRepository("User123");
        assertEquals(githubUserDto, user123);
    }
}
