package com.example.twitter_api.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Timelines implements Serializable {

    private String timelineResponse;
    private Date timelineResponseDate;
    private String timeLineResponseId;
    private String timeLineProfileImageUrl;

    public Timelines() {
    }

    public Timelines(String timeLineResponseId, String timeLineProfileImageUrl, String timelineResponse, Date timelineResponseDate) {
        this.timeLineResponseId = timeLineResponseId;
        this.timeLineProfileImageUrl = timeLineProfileImageUrl;
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
    public String getTimeLineProfileImageUrl() {
        return timeLineProfileImageUrl;
    }

    public void setTimeLineProfileImageUrl(String timeLineProfileImageUrl) {
        this.timeLineProfileImageUrl = timeLineProfileImageUrl;
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
