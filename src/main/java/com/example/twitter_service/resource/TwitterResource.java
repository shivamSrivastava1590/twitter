package com.example.twitter_service.resource;

import com.codahale.metrics.annotation.Timed;
import com.example.caching.ApplicationCache;
import com.example.caching.model.CachedResponse;
import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.TweetResponse;
import com.example.twitter_api.models.message.User;
import com.example.twitter_client.TwitterDriver;
import io.dropwizard.jersey.caching.CacheControl;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {

    private TwitterDriver twitterDriver;
    private ApplicationCache applicationCache;

    @Inject
    public TwitterResource(TwitterDriver twitterDriver, ApplicationCache applicationCache) {
        this.twitterDriver = twitterDriver;
        this.applicationCache = applicationCache;
    }

    @POST
    @Path("/tweet")
    @Timed
    public TweetResponse postTweetMessage(@DefaultValue("") @QueryParam("tweet") String tweet) {
        log.info("Posting tweet message");
        TweetResponse tweetResponse = new TweetResponse();
        User user = new User();
        Status status = null;
        Twitter twitter = null;
        try {
            twitter = twitterDriver.getTwitterHandle();
            status = twitter.updateStatus(tweet);
            user.setName(twitter.getScreenName());
            user.setProfileImageUrl(twitter.showUser(twitter.getId()).getProfileImageURL());
            tweetResponse.setCreatedAt(status.getCreatedAt().toString());
            tweetResponse.setUser(user);
            tweetResponse.setStatus(Response.Status.CREATED);
            tweetResponse.setMessage("Successfully updated the status to : " + status.getText());
        } catch (TwitterException e) {
            throw new WebApplicationException(e.getMessage(), e.getStatusCode());
        }
        return tweetResponse;
    }

    @GET
    @Timed
    @Path("/timeline")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public TimeLineResponse getTimeline(@DefaultValue("") @QueryParam("filter") String filter) {
        log.info("Getting user timeline");
        TimeLineResponse timeLineResponse = new TimeLineResponse();
        Optional<CachedResponse> timeLineResponseOptional = applicationCache.getResponseFromCache(filter);
        if (timeLineResponseOptional.isPresent()) {
            return timeLineResponseOptional.get().getTimeLineResponse();
        }
        List<String> timeLineList = Collections.emptyList();
        timeLineResponse.setTimeLineResponse(timeLineList);
        Twitter twitter = null;
        try {
            twitter = twitterDriver.getTwitterHandle();
            timeLineList = twitter.getHomeTimeline()
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
        applicationCache.updateCache(filter, timeLineResponse);
        return timeLineResponse;
    }
}