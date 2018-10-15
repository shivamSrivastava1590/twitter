package com.example.twitter_api.models.configuration;

import com.example.twitter_api.models.configuration.AuthConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class Configuration {

    private AuthConfig authConfig;

}