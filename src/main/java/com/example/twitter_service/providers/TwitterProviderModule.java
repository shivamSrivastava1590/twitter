package com.example.twitter_service.providers;

import com.example.caching.ApplicationCache;
import com.example.twitter_client.TwitterDriver;
import com.example.twitter_service.service.TwitterService;
import com.example.twitter_service.service.TwitterServiceImpl;
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

    @Provides
    public static TwitterService provideTwitterServiceImpl() {
        return new TwitterServiceImpl(provideTwitterDriver(), provideApplicationCache());
    }
}