package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Url {

        @JsonProperty("url")
        public String url;
        @JsonProperty("expanded_url")
        public String expandedUrl;
        @JsonProperty("display_url")
        public String displayUrl;
        @JsonProperty("indices")
        public List<Integer> indices = null;

        @Override
        public String toString() {
            return "Url{" +
                    "url='" + url + '\'' +
                    ", expandedUrl='" + expandedUrl + '\'' +
                    ", displayUrl='" + displayUrl + '\'' +
                    ", indices=" + indices +
                    '}';
        }
    }
