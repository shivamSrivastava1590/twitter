package com.example.twitter_service.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.TweetResponse;
import com.example.twitter_service.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import twitter4j.TwitterException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Slf4j
@Path("/1.0/twitter")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class TwitterResource {

    private TwitterService twitterService;

    @Inject
    public TwitterResource(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @POST
    @Path("/tweet")
    @Timed
    public TweetResponse postTweetMessage(@DefaultValue("") @QueryParam("tweet") String tweet){
        log.info("Posting tweet message");
        TweetResponse tweetResponse = new TweetResponse();
        try {
            tweetResponse = twitterService.postTweetMessage(tweet);
            if (tweetResponse.getStatus().equals(Response.Status.CREATED)) {
                log.info("Successfully posted tweet message : " + tweetResponse.getMessage());
                return tweetResponse;
            }
        } catch (TwitterException e) {
            tweetResponse.setMessage(e.getMessage());
            tweetResponse.setStatus(Response.Status.FORBIDDEN);
            log.error("Error occured while publishing the tweet: " + e.getMessage(), e);
        }
        return tweetResponse;
    }

    @GET
    @Timed
    @Path("/timeline")
    public TimeLineResponse getTimeline(@DefaultValue("") @QueryParam("filter") String filter) {
        log.info("Getting user timeline");
        TimeLineResponse timeLineResponse = new TimeLineResponse();
        try {
            timeLineResponse = twitterService.getTimeline(filter);
            if (timeLineResponse.getStatus().equals(Response.Status.OK)) {
                log.info("Successfully Retrived timeline : " + timeLineResponse.getStatus());
                return timeLineResponse;
            }
        } catch (TwitterException e) {
            log.error("Unable to fetch timeline : " + e.getMessage());
            timeLineResponse.setStatus(Response.Status.SERVICE_UNAVAILABLE);
            timeLineResponse.setTimelineResponse(Collections.emptyList());
        }
        return timeLineResponse;
    }
}