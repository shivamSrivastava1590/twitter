package com.example.twitter_service.component;

import com.example.twitter_service.providers.TwitterProviderModule;
import com.example.twitter_service.resource.TwitterResource;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = TwitterProviderModule.class)
public interface TwitterComponent {
    TwitterResource buildResource();
}