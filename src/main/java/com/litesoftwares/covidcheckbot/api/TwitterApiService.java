package com.litesoftwares.covidcheckbot.api;


import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionCount;
import com.litesoftwares.covidcheckbot.api.domain.subscription.SubscriptionList;
import com.litesoftwares.covidcheckbot.api.domain.webhook.Webhook;
import com.litesoftwares.covidcheckbot.api.domain.webhook.WebhookList;
import retrofit2.Call;
import retrofit2.http.*;

public interface TwitterApiService {

    // WEBHOOKS

    @POST("1.1/account_activity/all/{envName}/webhooks.json")
    Call<Webhook> registerWebhook(@Path("envName") String envName,
                                  @Query("url") String webhookUrl);

    @GET("1.1/account_activity/all/webhooks.json")
    Call<WebhookList> listWebhooks();

    @GET("1.1/account_activity/all/{envName}/webhooks.json")
    Call<WebhookList> listWebhooks(@Path("envName") String envName);

    @DELETE("1.1/account_activity/all/{envName}/webhooks/{webhookId}.json")
    Call<Void> deleteWebhook(@Path("envName") String envName, @Path("webhookId") String webhookId);

    @PUT("1.1/account_activity/all/{envName}/webhooks/{webhookId}.json")
    Call<Void> triggerCRC(@Path("envName") String envName, @Path("webhookId") String webhookId);

    //SUBSCRIPTIONS

    @POST("1.1/account_activity/all/{envName}/subscriptions.json")
    Call<Void> subscribe(@Path("envName") String envName);

    @GET("1.1/account_activity/all/subscriptions/count.json")
    Call<SubscriptionCount> subscriptionCount();

    @GET("1.1/account_activity/all/{envName}/subscriptions.json")
    Call<Void> checkSubscription(@Path("envName") String envName);

    @GET("1.1/account_activity/all/{envName}/subscriptions/list.json")
    Call<SubscriptionList> listSubscriptions(@Path("envName") String envName);

    @DELETE("1.1/account_activity/all/{envName}/subscriptions/{userId}.json")
    Call<Void> unsubscribe(@Path("envName") String envName, @Path("userId") String userId);

    @POST("1.1/direct_messages/indicate_typing.json")
    Call<Void> indicateTyping(@Query("recipient_id") String recipientId);
}
