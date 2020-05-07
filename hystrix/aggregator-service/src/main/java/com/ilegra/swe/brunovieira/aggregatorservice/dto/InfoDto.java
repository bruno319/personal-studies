package com.ilegra.swe.brunovieira.aggregatorservice.dto;

public class InfoDto {

    private String twitterScreenName;
    private int tweets;
    private String githubUsername;
    private int repositories;

    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public int getTweets() {
        return tweets;
    }

    public void setTweets(int tweets) {
        this.tweets = tweets;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public int getRepositories() {
        return repositories;
    }

    public void setRepositories(int repositories) {
        this.repositories = repositories;
    }
}
