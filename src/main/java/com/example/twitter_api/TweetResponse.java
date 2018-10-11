package com.example.twitter_api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

public class TweetResponse {
    private String message;
    private Response.Status status;

    @JsonProperty
    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}