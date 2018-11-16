package com.example.twitter_service.service;

import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.TweetResponse;
import twitter4j.TwitterException;

public interface TwitterService {

    TweetResponse postTweetMessage(String tweet) throws TwitterException;

    TimeLineResponse getTimeline(String filter) throws TwitterException;

}
