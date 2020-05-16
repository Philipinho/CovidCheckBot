package com.litesoftwares.covidcheckbot.model.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectMessageEvent {

    @JsonProperty("type")
    public String type;
    @JsonProperty("id")
    public String id;
    @JsonProperty("created_timestamp")
    public String createdTimestamp;
    @JsonProperty("message_create")
    public MessageCreate messageCreate;

    @Override
    public String toString() {
        return "DirectMessageEvent{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", createdTimestamp='" + createdTimestamp + '\'' +
                ", messageCreate=" + messageCreate +
                '}';
    }
}
