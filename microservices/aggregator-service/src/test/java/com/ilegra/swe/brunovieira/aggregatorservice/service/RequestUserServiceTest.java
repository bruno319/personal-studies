package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.dto.TweetsDto;
import com.ilegra.swe.brunovieira.aggregatorservice.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestUserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RequestUserService requestUserService;

    @Test
    public void shouldDoRequestAndReturnObject() throws UserNotFoundException {
        String url = "http://localhost:8181/tweets/User123";
        TweetsDto tweetsDto = mock(TweetsDto.class);
        when(restTemplate.getForObject(eq(url), same(TweetsDto.class)))
                .thenReturn(tweetsDto);

        Object response = requestUserService.doRequest(url, TweetsDto.class);
        assertEquals(tweetsDto, response);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionIfRequestIsNotSuccessful() throws UserNotFoundException {
        when(restTemplate.getForObject(anyString(), same(TweetsDto.class)))
                .thenThrow(HttpClientErrorException.class);

        requestUserService.doRequest("Invalid.Url", TweetsDto.class);
    }
}
