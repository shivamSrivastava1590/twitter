package com.example.twitter_service.providers;

import com.example.caching.ApplicationCache;
import com.example.twitter_client.TwitterDriver;
import dagger.Module;
import dagger.Provides;

@Module
public class TwitterProviderModule {

    @Provides
    public static TwitterDriver provideTwitterDriver() {
        return new TwitterDriver();
    }

    @Provides
    public static ApplicationCache provideApplicationCache() {
        return new ApplicationCache();
    }
}