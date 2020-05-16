package com.litesoftwares.covidcheckbot.data;

import com.vdurmont.emoji.EmojiParser;

public class Message {

    public static String submissionResponse(){
        String[] messages = {"Your report has been received.\nWe'll get back to you soonest." +
                "\n\nOur team of independent fact checkers will proceed to make some research so as to confirm or debunk the submitted info." +
                "\n\nPlease do not share this info with your peers yet so as to prevent spreading of a potential misinformation." +
                "\nThank you for helping us fight misinformation. "};
        int rand = (int)(messages.length * Math.random());

        return messages[rand];
    }
    //I only support a basic set of commands, send 'help' to review those...
    public static String helpMessage(){
        String message = "CovidCheckBot allows you to report COVID-19 information for fact checking and to check for latest COVID-19 statistics." +
                "\n\n<b>Commands</b>\n" +
                         "/report - report misinformation for fact checking \n" +
                         "/stats - check latest COVID-19 statistics \n" +
                         "/country - check cases of individual countries\n" +
                         "/help - info on available commands\n\n"
                + getLinks();

        return message;
    }

    public static String getLinks(){
        return "For latest updates and health information concerning the COVID-19 pandemic, please visit any of the following links "
                + EmojiParser.parseToUnicode(":point_down:") +
                "\n\nhttps://who.int/coronavirus\nhttps://cdc.gov/nCoV\nhttps://nhs.uk/coronavirus\nhttps://google.com/covid19";
    }

    public static String twitterLists(){
        String lists = "Recommended COVID-19 Twitter Lists" +
                "\n\nCOVID-19 Experts/Centers by @JohnsHopkins Uni" +
                "\nhttps://twitter.com/i/lists/1235659868565995520\n" +

                "\n\nCOVID19 News & Views by @wellcometrust" +
                "\nhttps://twitter.com/i/lists/1240652229507526656" +

                "\n\nCoronavirus by @abebrown716" +
                "\nhttps://twitter.com/i/lists/1238873758506668036" +
                "" +
                "\n\nTo suggest a list please send a DM to @PhilipOfficial9";

        return lists;
    }

}
