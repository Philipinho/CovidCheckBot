package com.litesoftwares.covidcheckbot.api;

import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionCount;
import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionList;
import com.litesoftwares.covidcheckbot.api.domain.webhook.Webhook;
import com.litesoftwares.covidcheckbot.api.domain.webhook.WebhookList;

public interface TwitterApiClient{
    
    Webhook registerWebhook(String envName, String webhookUrl);

    WebhookList listWebhooks();
    
    WebhookList listWebhooks(String envName);
    
    Void deleteWebhook(String envName, String webhookId);
    
    Void triggerCRC(String envName, String webhookId);

    Void subscribe(String envName);

    SubscriptionCount subscriptionCount();

    Void checkSubscription(String envName);
    
    SubscriptionList listSubscriptions(String envName);
    
    Void unsubscribe(String envName, String userId);

    Void indicateTyping(String recipientId);
}