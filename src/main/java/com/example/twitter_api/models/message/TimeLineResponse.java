package com.example.twitter_api.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

public class TimeLineResponse implements Serializable{

    private List<String> timeLineResponse;
    private Response.Status status;

    public TimeLineResponse() {
    }

    public TimeLineResponse(List<String> timeLineResponse, Response.Status status) {
        this.timeLineResponse = timeLineResponse;
        this.status = status;
    }

    @JsonProperty
    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    @JsonProperty
    public List<String> getTimeLineResponse() {
        return timeLineResponse;
    }

    public void setTimeLineResponse(List<String> timeLineResponse) {
        this.timeLineResponse = timeLineResponse;
    }
}