package com.litesoftwares.covidcheckbot.api.utils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class Constant {
    private static Twitter twitter = TwitterFactory.getSingleton();
    public static String consumerKey = twitter.getConfiguration().getOAuthConsumerKey();
    public static String consumerSecret = twitter.getConfiguration().getOAuthConsumerSecret();
    public static String accessToken = twitter.getConfiguration().getOAuthAccessToken();
    public static String accessTokenSecret = twitter.getConfiguration().getOAuthAccessTokenSecret();
}
