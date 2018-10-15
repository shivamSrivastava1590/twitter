package com.example.twitter_service.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.TweetResponse;
import com.example.twitter_api.models.message.User;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {

    private String tweet;

    public TwitterResource(String tweet) {
        this.tweet = Optional.of(tweet).orElse("");
        log.info("Created twitter resource with data : {} ", tweet);
    }

    @POST
    @Path("/tweet")
    @Timed
    public TweetResponse postTweetMessage() {
        log.info("Posting tweet message");
        TweetResponse result = new TweetResponse();
        User user = new User();
        Status status = null;
        Twitter twitter = null;
        try {
            twitter = TwitterDriver.getTwitterHandle();
            status = twitter.updateStatus(tweet);
            user.setName(twitter.getScreenName());
            user.setProfileImageUrl(twitter.showUser(twitter.getId()).getProfileImageURL());
            result.setCreatedAt(status.getCreatedAt().toString());
            result.setUser(user);
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
    public TimeLineResponse getTimeline(@DefaultValue("") @QueryParam("filter") String filter) {
        log.info("Getting user timeline");
        TimeLineResponse timeLineResponse = new TimeLineResponse();
        List<String> timeLineList = Collections.emptyList();
        timeLineResponse.setTimeLineResponse(timeLineList);
        try {
            timeLineList = TwitterDriver.getTwitterHandle().getHomeTimeline()
                    .stream()
                    .map(Status::getText)
                    .filter(text -> text.contains(filter))
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