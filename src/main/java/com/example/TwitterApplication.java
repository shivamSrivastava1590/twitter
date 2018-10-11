package com.example;

import com.example.twitter_service.config.TwitterConfiguration;
import com.example.twitter_service.resource.TwitterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TwitterApplication extends Application<TwitterConfiguration> {

    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void run(TwitterConfiguration twitterConfiguration, Environment environment) throws Exception {
        final TwitterResource twitterResource = new TwitterResource(twitterConfiguration.getTweet());
        environment.jersey().register(twitterResource);
    }
}