package com.litesoftwares.covidcheckbot.api.impl;

import com.litesoftwares.covidcheckbot.api.TwitterApi;
import com.litesoftwares.covidcheckbot.api.TwitterApi2;
import com.litesoftwares.covidcheckbot.api.TwitterApiService;
import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionCount;
import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionList;
import com.litesoftwares.covidcheckbot.api.domain.webhook.Webhook;
import com.litesoftwares.covidcheckbot.api.domain.webhook.WebhookList;
import com.litesoftwares.covidcheckbot.api.TwitterApiClient;

public class TwitterApiClientImpl implements TwitterApiClient {
    private TwitterApiService twitterApiService;
    private TwitterApiService twitterApiService2;
    private TwitterApi twitterApi;
    private TwitterApi2 twitterApi2;

    public TwitterApiClientImpl(){
        this.twitterApi= new TwitterApi();
        this.twitterApi2 = new TwitterApi2();
        this.twitterApiService = twitterApi.createService(TwitterApiService.class);
        this.twitterApiService2 = twitterApi2.createService(TwitterApiService.class);
    }

    @Override
    public Webhook registerWebhook(String envName, String webhookUrl) {
        return twitterApi.executeSync(twitterApiService.registerWebhook(envName,webhookUrl));
    }

    @Override
    public WebhookList listWebhooks() {
        return twitterApi2.executeSync(twitterApiService2.listWebhooks());
    }

    @Override
    public WebhookList listWebhooks(String envName) {
        return twitterApi2.executeSync(twitterApiService.listWebhooks(envName));
    }

    @Override
    public Void deleteWebhook(String envName, String webhookId) {
        return twitterApi.executeSync(twitterApiService.deleteWebhook(envName,webhookId));
    }

    @Override
    public Void triggerCRC(String envName, String webhookId) {
        return twitterApi.executeSync(twitterApiService.triggerCRC(envName,webhookId));
    }

    @Override
    public Void subscribe(String envName) {
        return twitterApi.executeSync(twitterApiService.subscribe(envName));
    }

    @Override
    public SubscriptionCount subscriptionCount() {
        return twitterApi2.executeSync(twitterApiService2.subscriptionCount());
    }

    @Override
    public Void checkSubscription(String envName) {
        return twitterApi.executeSync(twitterApiService.checkSubscription(envName));
    }

    @Override
    public SubscriptionList listSubscriptions(String envName) {
        return twitterApi2.executeSync(twitterApiService2.listSubscriptions(envName));
    }

    @Override
    public Void unsubscribe(String envName, String userId) {
        return twitterApi2.executeSync(twitterApiService2.unsubscribe(envName,userId));
    }

    @Override
    public Void indicateTyping(String recipientId) {
        return twitterApi.executeSync(twitterApiService.indicateTyping(recipientId));
    }

}
