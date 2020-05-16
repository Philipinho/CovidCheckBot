package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageData {

    @JsonProperty("text")
    public String text;
    @JsonProperty("entities")
    public Entities entities;

    @Override
    public String toString() {
        return "MessageData{" +
                "text='" + text + '\'' +
                ", entities=" + entities +
                '}';
    }
}
