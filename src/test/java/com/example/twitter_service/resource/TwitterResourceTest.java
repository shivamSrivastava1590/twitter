package com.example.twitter_service.resource;

import com.example.twitter_api.models.message.TimeLineResponse;
import com.example.twitter_api.models.message.TweetResponse;
import com.example.twitter_service.service.TwitterService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterResourceTest {

    private static final String TEST_TWEET = "tweet";
    private static final String FAILED_TWEET_MESSAGE = "tweet failed";
    private static final String EMPTY_STRING = "";
    private static final String FAILED_TIMELINE_MESSAGE = "timeline failed";

    @Mock
    private TwitterService twitterService;

    @InjectMocks
    private TwitterResource twitterResource = new TwitterResource(twitterService);


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void successTweetPostTest() throws TwitterException {

        TweetResponse tweetResponseMock = mock(TweetResponse.class);
        when(tweetResponseMock.getStatus()).thenReturn(Response.Status.CREATED);
        when(tweetResponseMock.getMessage()).thenReturn(TEST_TWEET);
        when(twitterService.postTweetMessage(TEST_TWEET)).thenReturn(tweetResponseMock);
        when(tweetResponseMock.getStatus()).thenReturn(Response.Status.CREATED);
        TweetResponse tweetResponse = twitterResource.postTweetMessage(TEST_TWEET);

        assertThat(tweetResponse.getStatus()).isEqualByComparingTo(Response.Status.CREATED);
        assertThat(tweetResponse.getMessage()).isEqualTo(TEST_TWEET);
    }

    @Test
    public void failedTweetPostTest() throws TwitterException {

        TweetResponse tweetResponseMock = mock(TweetResponse.class);
        when(tweetResponseMock.getMessage()).thenReturn(FAILED_TWEET_MESSAGE);
        when(tweetResponseMock.getStatus()).thenReturn(Response.Status.FORBIDDEN);
        when(twitterService.postTweetMessage(TEST_TWEET)).thenThrow(new TwitterException(FAILED_TWEET_MESSAGE));
        TweetResponse tweetResponse = twitterResource.postTweetMessage(TEST_TWEET);

        assertThat(tweetResponse.getMessage()).isEqualTo(FAILED_TWEET_MESSAGE);
        assertThat(tweetResponse.getStatus()).isEqualByComparingTo(Response.Status.FORBIDDEN);
    }

    @Test
    public void successGetTimelineTest() throws TwitterException {

        TimeLineResponse timeLineResponse = mock(TimeLineResponse.class);
        when(timeLineResponse.getStatus()).thenReturn(Response.Status.OK);
        when(twitterService.getTimeline(EMPTY_STRING)).thenReturn(timeLineResponse);
        TimeLineResponse response = twitterResource.getTimeline(EMPTY_STRING);

        assertThat(response.getStatus()).isEqualByComparingTo(Response.Status.OK);
    }

    @Test
    public void failedGetTimelineTest() throws TwitterException {
        TimeLineResponse timeLineResponse = mock(TimeLineResponse.class);
        when(timeLineResponse.getStatus()).thenReturn(Response.Status.SERVICE_UNAVAILABLE);
        when(timeLineResponse.getTimelineResponse()).thenReturn(Collections.emptyList());
        when(twitterService.getTimeline(EMPTY_STRING)).thenThrow(new TwitterException(FAILED_TIMELINE_MESSAGE));
        TimeLineResponse response = twitterResource.getTimeline(EMPTY_STRING);

        assertThat(response.getStatus()).isEqualByComparingTo(Response.Status.SERVICE_UNAVAILABLE);
        assertThat(response.getTimelineResponse()).isEmpty();
    }
}
