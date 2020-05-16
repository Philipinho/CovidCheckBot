package com.litesoftwares.covidcheckbot.controller;

import com.litesoftwares.covidcheckbot.service.TwitterWebhookService;
import com.litesoftwares.covidcheckbot.exception.ApiException;
import com.litesoftwares.covidcheckbot.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebhookController {

    @Autowired
    private TwitterService twitterService;
    @Autowired
    private TwitterWebhookService webhookService;

    @RequestMapping(value = "twitter/dm/listen",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> receiver(HttpServletRequest request) {

        String crcToken = request.getParameter("crc_token");
        if (crcToken.isEmpty()){
            throw new ApiException("CRC Token is empty.");
        }

        Map<String, String> responseJson = new HashMap<>();
        responseJson.put("response_token", twitterService.getCRCTokenChallenge(crcToken));

        return responseJson;
    }

    @RequestMapping(value = "twitter/dm/listen",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void streams(HttpEntity<String> response) {
        webhookService.processDMEvents(response);
    }

}
