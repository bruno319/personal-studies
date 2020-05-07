package com.ilegra.swe.brunovieira.githubservice.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class GithubUserDto {

    @JsonSetter("public_repos")
    private int publicRepositories;

    public int getPublicRepositories() {
        return publicRepositories;
    }

    public void setPublicRepositories(int publicRepositories) {
        this.publicRepositories = publicRepositories;
    }
}
