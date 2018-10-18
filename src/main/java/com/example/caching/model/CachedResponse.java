package com.example.caching.model;

import com.example.twitter_api.models.message.TimeLineResponse;
import lombok.ToString;

import java.io.*;
import java.time.Instant;

@ToString
public class CachedResponse implements Serializable {
    private Instant lastModified;
    private TimeLineResponse timeLineResponse;

    public CachedResponse() {
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public TimeLineResponse getTimeLineResponse() {
        return timeLineResponse;
    }

    public void setTimeLineResponse(TimeLineResponse timeLineResponse) {
        this.timeLineResponse = timeLineResponse;
    }
}
