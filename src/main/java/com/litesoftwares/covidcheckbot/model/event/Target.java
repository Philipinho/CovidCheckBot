package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Target {

        @JsonProperty("recipient_id")
        public String recipientId;

        @Override
        public String toString() {
            return "Target{" +
                    "recipientId='" + recipientId + '\'' +
                    '}';
        }
    }
