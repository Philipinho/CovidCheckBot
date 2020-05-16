package com.litesoftwares.covidcheckbot.api.domain.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {
    @JsonProperty("user_id")
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "userId=" + userId +
                '}';
    }
}

