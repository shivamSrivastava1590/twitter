package com.example.twitter;

import lombok.Getter;
import lombok.Setter;
import twitter4j.Twitter;

@Setter
@Getter
public class Twitterer {

    private Twitter twitter;

    public Twitterer() {
        TwitterDriver  twitterDriver = new TwitterDriver();
        twitter = twitterDriver.getTwitterHandle();
    }
}
