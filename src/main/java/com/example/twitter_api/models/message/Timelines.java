package com.example.twitter_api.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Timelines implements Serializable {

    private String timelineResponse;
    private Date timelineResponseDate;
    private String timeLineResponseId;

    public Timelines() {
    }

    public Timelines(String timeLineResponseId, String timelineResponse, Date timelineResponseDate) {
        this.timeLineResponseId = timeLineResponseId;
        this.timelineResponse = timelineResponse;
        this.timelineResponseDate = timelineResponseDate;
    }

    @JsonProperty
    public String getTimeLineResponseId() {
        return timeLineResponseId;
    }

    public void setTimeLineResponseId(String timeLineId) {
        this.timeLineResponseId = timeLineId;
    }

    @JsonProperty
    public String getTimelineResponse() {
        return timelineResponse;
    }

    public void setTimelineResponse(String timelineResponse) {
        this.timelineResponse = timelineResponse;
    }

    @JsonProperty
    public Date getTimelineResponseDate() {
        return timelineResponseDate;
    }

    public void setTimelineResponseDate(Date timelineResponseDate) {
        this.timelineResponseDate = timelineResponseDate;
    }
}
