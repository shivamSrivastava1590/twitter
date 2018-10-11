package com.example.twitter_client;

import com.example.twitter_api.Configuration;
import com.example.twitter_service.config.Config;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterDriver {

    private Configuration configuration = Config.getConfig();
    private ConfigurationBuilder configurationBuilder;
    private TwitterFactory twitterFactory;

    public TwitterDriver() {
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(configuration.getAuthConfig().getConsumerKey())
                .setOAuthConsumerSecret(configuration.getAuthConfig().getConsumerSecret())
                .setOAuthAccessToken(configuration.getAuthConfig().getAccessToken())
                .setOAuthAccessTokenSecret(configuration.getAuthConfig().getAccessTokenSecret());
        twitterFactory = new TwitterFactory(configurationBuilder.build());
    }

    public TwitterDriver(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
        twitterFactory = new TwitterFactory(configurationBuilder.build());
    }

    public Twitter getTwitterHandle() {
        return twitterFactory.getInstance();
    }
}