package com.ilegra.swe.brunovieira.githubservice.service;

import com.ilegra.swe.brunovieira.githubservice.dto.GithubUserDto;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class GithubService {

    private String githubApiUrl;
    private RestTemplate restTemplate;

    @Autowired
    public GithubService(
            RestTemplate restTemplate,
            @Value("${githubApiUrl}") String githubApiUrl) {
        this.restTemplate = restTemplate;
        this.githubApiUrl = githubApiUrl;
    }

    @HystrixCommand(fallbackMethod = "githubUserDtoFallback")
    public GithubUserDto getNumberRepository(String username) {
        return restTemplate.getForObject(githubApiUrl + username, GithubUserDto.class);
    }

    @SuppressWarnings("unused")
    private GithubUserDto githubUserDtoFallback(String username) {
        GithubUserDto githubUserDto = new GithubUserDto();
        githubUserDto.setPublicRepositories(-1);
        return githubUserDto;
    }
}
