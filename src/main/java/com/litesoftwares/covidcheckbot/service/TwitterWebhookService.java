package com.litesoftwares.covidcheckbot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litesoftwares.covidcheckbot.api.TwitterApiClient;
import com.litesoftwares.covidcheckbot.api.impl.TwitterApiClientImpl;
import com.litesoftwares.covidcheckbot.corona_api.CoronaAPIClient;
import com.litesoftwares.covidcheckbot.corona_api.model.Country;
import com.litesoftwares.covidcheckbot.data.Message;
import com.litesoftwares.covidcheckbot.model.event.DirectMessageEvent;
import com.litesoftwares.covidcheckbot.model.event.Event;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import twitter4j.*;

import java.text.DecimalFormat;
import java.util.List;

import static com.litesoftwares.covidcheckbot.utils.Util.formatNumber;
import static com.litesoftwares.covidcheckbot.utils.Util.getTime;

@Service
public class TwitterWebhookService {
    private Twitter twitter = TwitterFactory.getSingleton();
    @Autowired
    private TwitterService twitterService;
    @Autowired
    private DatabaseService db;
    private CoronaAPIClient cApi = new CoronaAPIClient();

    public void processDMEvents(HttpEntity<String> response){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = response.getBody();

            if (!jsonString.isEmpty() && jsonString.contains("direct_message_events")) {

                Event messageEvent = objectMapper.readValue(jsonString, new TypeReference<Event>() {});

                List<DirectMessageEvent> directMessageEvents = messageEvent.directMessageEvents;

                for (DirectMessageEvent dmEvent : directMessageEvents){
                    //System.out.println(dmEvent);
                    //System.out.println(dmEvent.type);
                    if (dmEvent.type.equals("message_create") && !dmEvent.messageCreate.senderId.equals(String.valueOf(twitter.getId()))){

                        String senderId = dmEvent.messageCreate.senderId;

                        User userInfo = twitter.showUser(Long.valueOf(senderId));
                        String platform = "twitter";
                        String message = messageEvent.directMessageEvents.get(0).messageCreate.messageData.text;
                        DecimalFormat number = new DecimalFormat("#,###");

                        QuickReply menuButton = new QuickReply("Menu", "Click here to see available commands", "menu");

                        if (!db.isRegistered(userInfo.getId())) {
                            db.registerUser(userInfo.getId(), userInfo.getScreenName(), EmojiParser.removeAllEmojis(userInfo.getName()), platform);
                        }

                        twitterService.indicateTyping(senderId);

                        if (message.equalsIgnoreCase("report")) {
                            db.updateStateReply("1", Long.valueOf(senderId));
                            twitter.sendDirectMessage(Long.parseLong(senderId), "Please enter information to report:", menuButton);
                        }

                        else if (message.equalsIgnoreCase("menu")) {
                            twitter.sendDirectMessage(Long.parseLong(senderId), "Please use any of the menu buttons below.\n" +
                                    "\nThis service is to enable you report questionable information concerning the COVID-19 for fact checking, and to check for latest COVID-19 statistics." +
                                    "" +
                                    "\n\n" + Message.getLinks(), getMenu());
                        }

                        else if (db.fetchStateReply(Long.valueOf(senderId)).equalsIgnoreCase("1")) {
                            db.saveReport(Long.parseLong(senderId), userInfo.getScreenName(), userInfo.getName(), message, platform);
                            db.updateStateReply("", Long.valueOf(senderId));
                            twitter.sendDirectMessage(Long.parseLong(senderId), Message.submissionResponse(), menuButton);
                        }

                        else if (message.equalsIgnoreCase("country")) {
                            db.updateStateReply("2", Long.valueOf(senderId));
                            twitter.sendDirectMessage(Long.parseLong(senderId),
                                    "Please enter country name to check statistics (e.g USA):", menuButton);
                        }

                        else if (db.fetchStateReply(Long.valueOf(senderId)).equalsIgnoreCase("2")) {
                            Country countryData = cApi.getCountry(message);
                            String statsMessage = "";

                            if (countryData != null) {
                                statsMessage = "Country: " + countryData.getCountry() +
                                        "\nTotal Cases: " + formatNumber(countryData.getCases()) +
                                        "\nActive Cases: " +  formatNumber(countryData.getActive()) +
                                        "\nNew Cases: " +  formatNumber(countryData.getTodayCases()) +
                                        "\nRecovered: " +  formatNumber(countryData.getRecovered()) +
                                        "\nDeaths: " +  formatNumber(countryData.getDeaths()) +
                                        "\nNew Deaths: " +  formatNumber(countryData.getTodayDeaths()) +
                                        "\nUpdated: " +  getTime(countryData.getUpdated()) +
                                        "\n\nSource: https://worldometers.info/coronavirus/";

                                twitter.sendDirectMessage(Long.parseLong(senderId), statsMessage, menuButton);
                                db.updateStateReply("", Long.valueOf(senderId));
                            }

                            else {
                                statsMessage = "Sorry, country not found or doesn't have any cases";
                                twitter.sendDirectMessage(Long.parseLong(senderId), statsMessage, getMenu());
                                db.updateStateReply("", Long.valueOf(senderId));
                            }
                        }

                        else if (message.equalsIgnoreCase("Stats")) {
                            String statsMessage =
                                    "Affected Countries: " + formatNumber(cApi.getTotalStats().getAffectedCountries()) +
                                    "\nTotal Cases: " + formatNumber(cApi.getTotalStats().getCases()) +
                                            "\nActive Cases: " +  formatNumber(cApi.getTotalStats().getActiveCases()) +
                                            "\nNew Cases: " +  formatNumber(cApi.getTotalStats().getNewCases()) +
                                            "\nRecovered: " +  formatNumber(cApi.getTotalStats().getRecovered()) +
                                            "\nDeaths: " +  formatNumber(cApi.getTotalStats().getDeaths()) +
                                            "\nNew Deaths: " +  formatNumber(cApi.getTotalStats().getNewDeaths()) +
                                            "\nUpdated: " +  getTime(cApi.getTotalStats().getUpdated()) +
                                            "\n\nSource: https://worldometers.info/coronavirus/";
                            twitter.sendDirectMessage(Long.parseLong(senderId), statsMessage, menuButton);
                        }

                        else if (message.equalsIgnoreCase("Twitter Lists")) {
                            twitter.sendDirectMessage(Long.parseLong(senderId), Message.twitterLists(), menuButton);
                        }

                        else if (message.toLowerCase().equals("hi") || message.toLowerCase().equals("hello")) {

                            String msg = "Hi, please use the commands on the menu buttons below.";
                            twitter.sendDirectMessage(Long.parseLong(senderId), msg, getMenu());

                        } else {

                            String msg = "Sorry, I didn't understand your reply." +
                                    "\nPlease use the commands on the menu buttons below.";

                            twitter.sendDirectMessage(Long.parseLong(senderId), msg, getMenu());
                        }
                    }
                }
            }
        } catch (Exception e){
            if (!e.getMessage().contains("Index: 0, Size: 0")) {
                System.out.println(e.getMessage());
            }
        }
    }

    private QuickReply[] getMenu(){
        QuickReply statsReply = new QuickReply("Stats","Latest Global COVID-19 statistics",
                "covid_stats");

        QuickReply countryReply = new QuickReply("Country","Check cases of individual countries",
                "country");

        QuickReply reportReply = new QuickReply("Report","Click here to report information for fact-checking.",
                "report");

        QuickReply twitterListReply = new QuickReply("Twitter Lists","Recommended COVID-19 Twitter Lists.",
                "twitter");

        return  new QuickReply[]{reportReply,statsReply,countryReply,twitterListReply};
    }

    @Scheduled(cron = "0 0 0/12 * * *") //Refresh every 12 hours
    public void refreshToken(){
        TwitterApiClient client = new TwitterApiClientImpl();
        try {
            String envName = client.listWebhooks().getEnvironments().get(0).getEnvironmentName();
            long webhookId = client.listWebhooks().getEnvironments().get(0).getWebhooks().get(0).getId();
            client.triggerCRC(envName, String.valueOf(webhookId));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
