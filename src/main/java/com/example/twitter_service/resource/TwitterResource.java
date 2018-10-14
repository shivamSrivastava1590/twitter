package com.example.twitter_service.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.twitter_api.TimeLineResponse;
import com.example.twitter_api.TweetResponse;
import com.example.twitter_client.TwitterDriver;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {

    private TwitterDriver twitterDriver;
    private String tweet;

    public TwitterResource(String tweet) {
        log.info("Created twitter resource with data : {} ", tweet);
        twitterDriver = new TwitterDriver();
        this.tweet = tweet;
    }

    @POST
    @Path("/tweet")
    @Timed
    public TweetResponse postTweetMessage() {
        log.info("Posting tweet message");
        TweetResponse result = new TweetResponse();
        Twitter twitter = twitterDriver.getTwitterHandle();
        try {
            Status status = twitter.updateStatus(tweet);
            result.setStatus(Response.Status.CREATED);
            result.setMessage("Successfully updated the status to : " + status.getText());
        } catch (TwitterException e) {
            throw new WebApplicationException(e.getMessage(), e.getStatusCode());
        }
        return result;
    }

    @GET
    @Timed
    @Path("/timeline")
    public TimeLineResponse getTimeline() {
        log.info("Getting user timeline");
        TimeLineResponse timeLineResponse = new TimeLineResponse();
        List<String> timeLineList = Collections.emptyList();
        timeLineResponse.setTimeLineResponse(timeLineList);
        Twitter twitter = twitterDriver.getTwitterHandle();
        try {
            timeLineList = twitter.getHomeTimeline()
                    .stream()
                    .map(Status::getText)
                    .collect(Collectors.toList());
            if (timeLineList.size() == 0) {
                throw new WebApplicationException("There are no messages in your timeline" , Response.Status.NOT_FOUND);
            }
            log.debug("Timeline received : {} ", timeLineList.toString());
            timeLineResponse.setTimeLineResponse(timeLineList);
            timeLineResponse.setStatus(Response.Status.OK);
        } catch (TwitterException e) {
            throw new WebApplicationException(e.getMessage(), e.getStatusCode());
        }
        return timeLineResponse;
    }
}