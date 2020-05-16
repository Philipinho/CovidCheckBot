package com.litesoftwares.covidcheckbot.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Users {
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<>();

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
        public String toString() {
            return "Users{" +
                    "additionalProperties=" + additionalProperties +
                    '}';
        }
    }
