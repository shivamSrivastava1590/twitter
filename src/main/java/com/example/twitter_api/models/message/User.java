package com.example.twitter_api.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String name;
    private String profileImageUrl;

    @JsonProperty
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
