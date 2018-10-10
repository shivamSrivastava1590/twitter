package com.example.config.model;

import com.example.config.model.auth.AuthConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public final class Configuration {

    private AuthConfig authConfig;

}