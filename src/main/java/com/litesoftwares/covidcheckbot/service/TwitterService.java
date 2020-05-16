package com.litesoftwares.covidcheckbot.service;

import com.litesoftwares.covidcheckbot.api.utils.Constant;
import com.litesoftwares.covidcheckbot.api.TwitterApiClient;
import com.litesoftwares.covidcheckbot.api.impl.TwitterApiClientImpl;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class TwitterService {

    public void indicateTyping(String recipientId){
        TwitterApiClient client = new TwitterApiClientImpl();
        client.indicateTyping(recipientId);
    }

    public String getCRCTokenChallenge(String crcToken){
        String consumerSecret = Constant.consumerSecret;
        String responseToken = "";
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(consumerSecret.getBytes(),"HmacSHA256");
            sha256HMAC.init(secretKeySpec);

            responseToken = Base64.encodeBase64String(sha256HMAC.doFinal(crcToken.getBytes()));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return "sha256=" + responseToken;
    }
}
