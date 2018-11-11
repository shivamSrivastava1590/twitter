package com.example.twitter_api;

import com.example.twitter_api.models.message.TimeLineResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;


public class TimeLineResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    final static TimeLineResponse timeLineResponse = new TimeLineResponse();

    @BeforeClass
    public static void setUp() {
        timeLineResponse.setStatus(Response.Status.OK);
//        timeLineResponse.setTimeLineResponse(new ArrayList<>(Arrays.asList("response_1", "response_2")));
    }

    @Test
    public void serializeToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(new File("src/test/resources/fixtures/timelineresponse.json"), TimeLineResponse.class));
        assertThat(MAPPER.writeValueAsString(timeLineResponse)).isEqualTo(expected);
    }

    @Test
    public void deserializeFromJSON() throws Exception {
        assertThat(MAPPER.readValue(new File("src/test/resources/fixtures/timelineresponse.json"), TimeLineResponse.class)).isEqualToComparingFieldByField(timeLineResponse);
    }
}
