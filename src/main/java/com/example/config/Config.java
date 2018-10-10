package com.example.config;

import com.example.config.model.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class Config {

    private static final Configuration config = loadConfig();

    private Config() {
    }

    private static Configuration loadConfig() {
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