package com.example.twitter_service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class TwitterConfiguration extends Configuration{

    @NotEmpty
    private String tweet;

    @JsonProperty
    public String getTweet() {
        return tweet;
    }
}
