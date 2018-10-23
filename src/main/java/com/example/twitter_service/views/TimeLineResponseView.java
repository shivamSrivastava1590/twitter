package com.example.twitter_service.views;

import io.dropwizard.views.View;

import java.util.List;

public class TimeLineResponseView extends View {

    private final List<String> timelineResponses;

    public enum Template {
        TIMELINE_RESPONSE("timeline_response.mustache");

        private String templateName;

        Template(String templateName) {
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }

    public TimeLineResponseView(TimeLineResponseView.Template templateName, List<String> timelineResponses) {
        super(templateName.getTemplateName());
        this.timelineResponses = timelineResponses;
    }

    public List<String> getTimelineResponses() {
        return timelineResponses;
    }
}
