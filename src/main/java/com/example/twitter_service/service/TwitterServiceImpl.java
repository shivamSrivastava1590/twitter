package com.example.twitter_service.service;

import com.example.caching.ApplicationCache;
import com.example.caching.model.CachedResponse;
import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.Timelines;
import com.example.twitter_api.models.message.TweetResponse;
import com.example.twitter_api.models.message.User;
import com.example.twitter_client.TwitterDriver;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.*;

@Slf4j
public class TwitterServiceImpl implements TwitterService {

    private TwitterDriver twitterDriver;
    private ApplicationCache applicationCache;

    @Inject
    public TwitterServiceImpl(TwitterDriver twitterDriver, ApplicationCache applicationCache) {
        this.twitterDriver = twitterDriver;
        this.applicationCache = applicationCache;
    }

    @Override
    public TweetResponse postTweetMessage(String tweet) throws TwitterException {
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
            log.error("Unable to post tweet : " + e.getMessage());
            throw new TwitterException(e);
        }
        return tweetResponse;
    }

    @Override
    public TimeLineResponse getTimeline(String filter) throws TwitterException {
        log.info("Getting user timeline");
        TimeLineResponse timeLineResponses = new TimeLineResponse();
        Optional<CachedResponse> timeLineResponseOptional = applicationCache.getResponseFromCache(filter);
        if (timeLineResponseOptional.isPresent()) {
            return timeLineResponseOptional.get().getTimeLineResponse();
        }
        List<Timelines> timelines = new ArrayList<>();
        timeLineResponses.setTimelineResponse(timelines);
        try {
            Twitter twitter = twitterDriver.getTwitterHandle();
            final Map<String, Map<String, Date>> timeLineWithId = new HashMap<>();
            for (Status timeline : twitter.getHomeTimeline()) {
                timeLineWithId.put(Long.toString(timeline.getId()) + " " + twitter.showUser(timeline.getUser().getId()).getProfileImageURL(),  new HashMap<String, Date>() {{
                    put(timeline.getText(), timeline.getCreatedAt());
                }});
            }
            if (timeLineWithId.size() == 0) {
                throw new TwitterException("There are no messages in your timeline : " + Response.Status.NOT_FOUND);
            }
            log.debug("Timeline received : {} ", timeLineWithId.toString());
            for (Map.Entry<String, Map<String, Date>> entry: timeLineWithId.entrySet()) {
                for (Map.Entry<String, Date> entryValue : entry.getValue().entrySet()) {
                    String[] timelineIdAndImage = entry.getKey().split(" ");
                    timelines.add(new Timelines(timelineIdAndImage[0], timelineIdAndImage[1], entryValue.getKey(), entryValue.getValue()));
                }
            }
            timeLineResponses.setStatus(Response.Status.OK);
        } catch (TwitterException e) {
            throw new TwitterException(e);
        }
        applicationCache.updateCache(filter, timeLineResponses);
        return timeLineResponses;
    }
}
