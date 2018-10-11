package com.example.twitter_api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {

    private long id;

    private String tweet;
    
    public Tweet(long id, String tweet) {
        this.id = id;
        this.tweet = tweet;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTweet() {
        return tweet;
    }
}
