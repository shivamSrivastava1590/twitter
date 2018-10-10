package com.example.app;

import com.example.twitter.Twitterer;
import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TwitterRunner {

    private static final String twitterMessage = "This is my automated twitter message";
    private Twitterer twitter = new Twitterer();

    public void postAtweet(final String tweetMessage) {
        Status status = null;
        try {
            status = twitter.getTwitter().updateStatus(tweetMessage);
            log.info("Successfully posted tweet : [" + status.getText() + " ]");
        } catch (TwitterException e) {
            log.error("Not able to post a tweet : [ " + status.getText() + " ]");
            e.printStackTrace();
        }
    }

    public List<String> getTimeLine() {
        List<String> timeLineList = Collections.emptyList();
        try {
            timeLineList = twitter.getTwitter().getHomeTimeline()
                    .stream()
                    .map(Status::getText)
                    .collect(Collectors.toList());
            if (timeLineList.size() == 0) {
                log.info("No messages on your timeline present");
            }
        } catch (TwitterException e) {
            log.error("Not able to get timeLine :" );
            e.printStackTrace();
        }
        return timeLineList;
    }

    public static void main(String[] args)  {
        TwitterRunner twitterRunner = new TwitterRunner();
        twitterRunner.postAtweet(twitterMessage);
        twitterRunner.getTimeLine().stream().forEach(timeLine -> System.out.println("==> " + timeLine));
    }
}