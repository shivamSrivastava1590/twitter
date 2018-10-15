package com.example.twitter_service.config;

import com.example.twitter_api.models.configuration.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Slf4j
public class Config {

    private static final Configuration config = loadConfig();

    private Config() {
    }

    private static Configuration loadConfig() {
        log.info("Loaded twitter config : twitter-config.yaml");
        Yaml yaml = new Yaml(new Constructor(Configuration.class));
        InputStream inputStream = Config.class
                .getClassLoader()
                .getResourceAsStream("yaml/twitter-config.yaml");
        return yaml.load(inputStream);
    }

    public static Configuration getConfig() {
        return config;
    }
}
