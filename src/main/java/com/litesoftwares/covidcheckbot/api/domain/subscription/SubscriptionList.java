package com.litesoftwares.covidcheckbot.api.domain.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class SubscriptionList {
       @JsonProperty("environment")
       private String environment;
       @JsonProperty("application_id")
       private long applicationId;
       @JsonProperty("subscription")
       private List<SubscriptionList> subscriptions = null;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public List<SubscriptionList> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionList> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "SubscriptionList{" +
                "environment='" + environment + '\'' +
                ", applicationId=" + applicationId +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
