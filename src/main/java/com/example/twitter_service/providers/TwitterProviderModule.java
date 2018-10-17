package com.example.twitter_service.providers;

import com.example.twitter_client.TwitterDriver;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class TwitterProviderModule {

    @Singleton
    @Provides
    public static TwitterDriver provideTwitterDriver() {
        return new TwitterDriver();
    }
}