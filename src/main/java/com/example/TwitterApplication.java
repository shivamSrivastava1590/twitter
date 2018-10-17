package com.example;

import com.example.twitter_service.component.DaggerTwitterComponent;
import com.example.twitter_service.config.TwitterConfiguration;
import com.example.twitter_service.resource.TwitterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
public class TwitterApplication extends Application<TwitterConfiguration>{

    public static void main(String[] args) throws Exception {
        log.debug("Starting twitter app with args : {}", Arrays.toString(args));
        new TwitterApplication().run(args);
    }

    @Override
    public void run(TwitterConfiguration twitterConfiguration, Environment environment) throws Exception {
        TwitterResource twitterComponent = DaggerTwitterComponent.create().buildResource();
        environment.jersey().register(twitterComponent);
    }
}