package com.litesoftwares.covidcheckbot.api.domain.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WebhookList {
    @JsonProperty("environments")
    private List<Environment> environments = null;

    public List<Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    @Override
    public String toString() {
        return "WebhookList{" +
                "environments=" + environments +
                '}';
    }
}
