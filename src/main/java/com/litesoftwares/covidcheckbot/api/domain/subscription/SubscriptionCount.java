package com.litesoftwares.covidcheckbot.api.domain.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCount {
    @JsonProperty("account_name")
    private String accountName;
    @JsonProperty("subscriptions_count")
    private int subscriptionCount;
    @JsonProperty("provisioned_count")
    private int provisionCount;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public int getProvisionCount() {
        return provisionCount;
    }

    public void setProvisionCount(int provisionCount) {
        this.provisionCount = provisionCount;
    }

    @Override
    public String toString() {
        return "SubscriptionCount{" +
                "accountName='" + accountName + '\'' +
                ", subscriptionCount=" + subscriptionCount +
                ", provisionCount=" + provisionCount +
                '}';
    }
}
