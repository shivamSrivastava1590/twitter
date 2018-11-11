package com.example.twitter_service.resource;

import com.example.twitter_api.models.message.TimeLineResponse;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TwitterResourceTest {

    private  TimeLineResponse timeLineResponseMock = Mockito.mock(TimeLineResponse.class);

    @Rule
    public  final ResourceTestRule resourcesRule = ResourceTestRule.builder().addResource(timeLineResponseMock).build();

    private TimeLineResponse timeLineResponse = new TimeLineResponse();

    @Before
    public void before() {
        timeLineResponse.setStatus(Response.Status.OK);
//        timeLineResponse.setTimeLineResponse(new ArrayList<>(Arrays.asList("response_1", "response_2")));
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {

        when(timeLineResponseMock.getStatus()).thenReturn(Response.Status.OK);
//        when(timeLineResponseMock.getTimeLineResponse()).thenReturn(new ArrayList<>(Arrays.asList("response_1", "response_2")));


//        TimeLineResponse timeLineResponse = resourcesRule.getJerseyTest().target("/api/1.0/twitter/timeline").request().get(TimeLineResponse.class);
    }
}
