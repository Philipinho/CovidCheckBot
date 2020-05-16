package com.litesoftwares.covidcheckbot.model.event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("for_user_id")
    public String forUserId;
    @JsonProperty("direct_message_events")
    public List<DirectMessageEvent> directMessageEvents = null;
    @JsonProperty("users")
    public Users users;

    @Override
    public String toString() {
        return "event{" +
                "forUserId='" + forUserId + '\'' +
                ", directMessageEvents=" + directMessageEvents +
                ", users=" + users +
                '}';
    }
}


