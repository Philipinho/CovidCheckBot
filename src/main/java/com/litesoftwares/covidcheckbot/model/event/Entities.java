package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Entities {

        @JsonProperty("hashtags")
        public List<Object> hashtags = null;
        @JsonProperty("symbols")
        public List<Object> symbols = null;
        @JsonProperty("user_mentions")
        public List<Object> userMentions = null;
        @JsonProperty("urls")
        public List<Url> urls = null;

        @Override
        public String toString() {
            return "Entities{" +
                    "hashtags=" + hashtags +
                    ", symbols=" + symbols +
                    ", userMentions=" + userMentions +
                    ", urls=" + urls +
                    '}';
        }
    }
