package com.example.twitter_client;

import com.example.twitter_api.models.configuration.Configuration;
import com.example.twitter_service.config.Config;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Slf4j
public class TwitterDriver {

    private static Configuration configuration = Config.getConfig();
    private static TwitterFactory twitter;
    private ConfigurationBuilder configurationBuilder;

    public TwitterDriver() {
        log.info("Configuring driver with default keys");
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(configuration.getAuthConfig().getConsumerKey())
                .setOAuthConsumerSecret(configuration.getAuthConfig().getConsumerSecret())
                .setOAuthAccessToken(configuration.getAuthConfig().getAccessToken())
                .setOAuthAccessTokenSecret(configuration.getAuthConfig().getAccessTokenSecret());
    }

    public Twitter getTwitterHandle() {
        if (twitter == null) {
            twitter = new TwitterFactory(configurationBuilder.build());
        }
        return twitter.getInstance();
    }
}