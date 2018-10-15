package com.example.twitter_client;

import com.example.twitter_api.Configuration;
import com.example.twitter_service.config.Config;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Slf4j
public class TwitterDriver {

    private static Configuration configuration = Config.getConfig();
    private static ConfigurationBuilder configurationBuilder = buildTwitterConfig();
    private static TwitterFactory twitter = new TwitterFactory(configurationBuilder.build());

    private TwitterDriver() {
    }

    private static ConfigurationBuilder buildTwitterConfig() {
        log.info("Configuring driver with default keys");
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(configuration.getAuthConfig().getConsumerKey())
                .setOAuthConsumerSecret(configuration.getAuthConfig().getConsumerSecret())
                .setOAuthAccessToken(configuration.getAuthConfig().getAccessToken())
                .setOAuthAccessTokenSecret(configuration.getAuthConfig().getAccessTokenSecret());
        return configurationBuilder;
    }

    public static Twitter getTwitterHandle() {
        return twitter.getInstance();
    }
}