package com.ilegra.swe.brunovieira.aggregatorservice.service;

import com.ilegra.swe.brunovieira.aggregatorservice.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
public class RequestUserService {

    private RestTemplate restTemplate;

    @Autowired
    public RequestUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object doRequest(String url, Class mapperClass) throws UserNotFoundException {
        try {
            return restTemplate.getForObject(url, mapperClass);
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException("User not found");
        }
    }
}
