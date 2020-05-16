package com.litesoftwares.covidcheckbot.service;

import com.litesoftwares.covidcheckbot.corona_api.CoronaAPIClient;
import com.litesoftwares.covidcheckbot.corona_api.model.Country;
import com.litesoftwares.covidcheckbot.data.Message;
import com.litesoftwares.covidcheckbot.utils.Util;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DecimalFormat;

import static com.litesoftwares.covidcheckbot.utils.Util.formatNumber;
import static com.litesoftwares.covidcheckbot.utils.Util.getTime;

@Component
@Service
public class TelegramService extends TelegramLongPollingBot {

    @Value("${telegrambot.token}")
    private String botToken;
    @Value("${telegrambot.username}")
    private String botUsername;
    private CoronaAPIClient cApi = new CoronaAPIClient();

    @Autowired
    private DatabaseService db;

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        String username = update.getMessage().getFrom().getUserName();
        String name = EmojiParser.removeAllEmojis(update.getMessage().getFrom().getFirstName());
        String platform = "telegram";
        DecimalFormat number = new DecimalFormat("#,###");

        if (update.hasMessage() && update.getMessage().hasText()) {


            if (messageText.equalsIgnoreCase("/start")){
                message.setChatId(chatId)
                        .setText("Hello @" + update.getMessage().getChat().getUserName() +
                                ", welcome to CovidCheckBot.\nThis bot allows you to report COVID-19 information for fact checking and to check for latest COVID-19 statistics."
                        + "\n\n<b>Commands</b>" +
                                "\n/report - report misinformation for fact checking" +
                                "\n/stats - check latest COVID-19 statistics" +
                                "\n/country - check cases of individual countries" +
                                "\n/help - info on available commands\n\n" +
                                "" + Message.getLinks()).enableHtml(true).disableWebPagePreview();

                if (!db.isRegistered(chatId)){
                    db.registerUser(chatId,username,name,platform);
                }
            } else if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/stats")){
                String statsMessage =
                        "<b>Affected Countries:</b> " + formatNumber(cApi.getTotalStats().getAffectedCountries()) +
                        "\n<b>Total Cases:</b> " + formatNumber(cApi.getTotalStats().getCases()) +
                                "\n<b>Active Cases:</b> " +  formatNumber(cApi.getTotalStats().getActiveCases()) +
                                "\n<b>New Cases:</b> " +  formatNumber(cApi.getTotalStats().getNewCases()) +
                                "\n<b>Recovered:</b> " +  formatNumber(cApi.getTotalStats().getRecovered()) +
                                "\n<b>Deaths:</b> " +  formatNumber(cApi.getTotalStats().getDeaths()) +
                                "\n<b>New Deaths:</b> " +  formatNumber(cApi.getTotalStats().getNewDeaths()) +
                                "\n<b>Updated:</b> " +  getTime(cApi.getTotalStats().getUpdated()) +
                                "\n\n<b>Source:</b> https://worldometers.info/coronavirus/";

                message.setChatId(chatId).setText(statsMessage).enableHtml(true).disableWebPagePreview();
            } else if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/report")){

                ForceReplyKeyboard forceReplyKeyboard = getForceReply();

                message.enableMarkdown(true);
                message.setChatId(chatId);
                message.setReplyToMessageId(update.getMessage().getMessageId());
                message.setReplyMarkup(forceReplyKeyboard);
                message.setText("Please enter your report:");

            } else if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/country")){
                ForceReplyKeyboard forceReplyKeyboard = getForceReply();

                message.enableMarkdown(true);
                message.setChatId(chatId);
                message.setReplyToMessageId(update.getMessage().getMessageId());
                message.setReplyMarkup(forceReplyKeyboard);
                message.setText("Please send country name (e.g USA)");
            } else if (update.getMessage().isReply() && update.getMessage().getReplyToMessage().getText().equalsIgnoreCase("Please enter your report:")){
                //report to save
                db.saveReport(chatId,username,name,update.getMessage().getText(),platform);
                message.setChatId(chatId).setText(Message.submissionResponse());
            } else if (update.getMessage().isReply() && update.getMessage().getReplyToMessage().getText().equalsIgnoreCase("Please send country name (e.g USA)")){

                String statsMessage = "";
                try {
                    String countryName = update.getMessage().getText();
                    Country countryData = cApi.getCountry(countryName);

                    statsMessage =
                                "<b>Country:</b> " + countryData.getCountry() +
                                "\n<b>Total Cases:</b> " + formatNumber(countryData.getCases()) +
                                "\n<b>Active Cases:</b> " +  formatNumber(countryData.getActive()) +
                                "\n<b>New Cases:</b> " +  formatNumber(countryData.getTodayCases()) +
                                "\n<b>Recovered:</b> " +  formatNumber(countryData.getRecovered()) +
                                "\n<b>Deaths:</b> " +  formatNumber(countryData.getDeaths()) +
                                "\n<b>New Deaths:</b> " +  formatNumber(countryData.getTodayDeaths()) +
                                "\n<b>Updated:</b> " +  getTime(countryData.getUpdated()) +
                                "\n\n<b>Source:</b> https://worldometers.info/coronavirus/";
                } catch (Exception e){
                    statsMessage = "Sorry, something went wrong. Please try again later. Thank you.";
                    e.printStackTrace();
                }

                message.setChatId(chatId).setText(statsMessage).enableHtml(true).disableWebPagePreview();
            }

            else if (update.getMessage().getText().equalsIgnoreCase("/help")
            || update.getMessage().getText().equalsIgnoreCase("menu") || update.getMessage().getText().equalsIgnoreCase("help")){
                message.setChatId(chatId).setText(Message.helpMessage()).enableHtml(true).disableWebPagePreview();
            }

            else {
                String msg = "Sorry, I didn't understand your reply." +
                        "\nPlease use the use the /help menu for available commands.";
                message.setChatId(chatId).setText(msg).enableHtml(true);
            }

        }

        if (update.getMessage().hasPhoto()){
            System.out.println(update.getMessage().getPhoto().get(0));
            message.setChatId(chatId).setText("Sorry, photo sharing is not yet supported. However, you can email it to us" +
                    " on hello@covidcheckbot.com");
        }

        if (update.getMessage().hasVideo()){
            message.setChatId(chatId).setText("Sorry, video sharing is not yet supported. However, you can email it to us" +
                    " on hello@covidcheckbot.com");
        }

        try {
            if (message.getChatId() != null) {
                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static ForceReplyKeyboard getForceReply() {
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        return forceReplyKeyboard;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
