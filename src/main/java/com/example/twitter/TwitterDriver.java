package com.example.twitter;

import com.example.config.Config;
import com.example.config.model.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterDriver {

    private Configuration configuration = Config.getConfig();
    private ConfigurationBuilder configurationBuilder;
    private Twitter twitter;

    public TwitterDriver() {
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(configuration.getAuthConfig().getConsumerKey())
                .setOAuthConsumerSecret(configuration.getAuthConfig().getConsumerSecret())
                .setOAuthAccessToken(configuration.getAuthConfig().getAccessToken())
                .setOAuthAccessTokenSecret(configuration.getAuthConfig().getAccessTokenSecret());
    }

    public TwitterDriver(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
    }

    public Twitter getTwitterHandle() {
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        return twitterFactory.getInstance();
    }
}