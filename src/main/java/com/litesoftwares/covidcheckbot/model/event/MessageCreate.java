package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class MessageCreate {

        @JsonProperty("target")
        public Target target;
        @JsonProperty("sender_id")
        public String senderId;
        @JsonProperty("message_data")
        public MessageData messageData;

        @Override
        public String toString() {
            return "MessageCreate{" +
                    "target=" + target +
                    ", senderId='" + senderId + '\'' +
                    ", messageData=" + messageData +
                    '}';
        }
    }
