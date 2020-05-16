package com.litesoftwares.covidcheckbot.api.domain.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Environment {
    @JsonProperty("environment_name")
    private String environmentName;
    @JsonProperty("webhooks")
    private List<Webhook> webhooks = null;

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "environmentName='" + environmentName + '\'' +
                ", webhooks=" + webhooks +
                '}';
    }
}
