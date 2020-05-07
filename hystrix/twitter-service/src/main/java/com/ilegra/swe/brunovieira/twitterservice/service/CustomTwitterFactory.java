package com.ilegra.swe.brunovieira.twitterservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
@PropertySource("classpath:oauth.properties")
public class CustomTwitterFactory {

    private TwitterFactory twitterFactory;

    public CustomTwitterFactory(
            @Value("${consumerKey}") String consumerKey,
            @Value("${consumerSecret}") String consumerSecret,
            @Value("${accessToken}") String accessToken,
            @Value("${accessTokenSecret}") String secretAccessToken
    ) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(false)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(secretAccessToken);
        twitterFactory = new TwitterFactory(configurationBuilder.build());
    }

    Twitter getTwitter() {
        return twitterFactory.getInstance();
    }
}
