package com.ilegra.swe.brunovieira.aggregatorservice.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AggregatorConfiguration.class})
public class AggregatorConfigurationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnRestTemplateInstance() {
        assertNotNull(restTemplate);
    }
}
