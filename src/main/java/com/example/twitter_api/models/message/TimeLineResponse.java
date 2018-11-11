package com.example.twitter_api.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

public class TimeLineResponse implements Serializable{

    private Response.Status status;
    private List<Timelines> timelineResponses;

    public TimeLineResponse() {
    }

    public TimeLineResponse(Response.Status status, List<Timelines> timelineResponses) {
        this.status = status;
        this.timelineResponses = timelineResponses;
    }

    @JsonProperty
    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    @JsonProperty
    public List<Timelines> getTimelineResponse() {
        return timelineResponses;
    }

    public void setTimelineResponse(List<Timelines> timelineResponses) {
        this.timelineResponses = timelineResponses;
    }
}