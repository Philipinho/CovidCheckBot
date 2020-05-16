package com.litesoftwares.covidcheckbot.api.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litesoftwares.covidcheckbot.api.domain.BearerToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

public class Utils {
    public static String getBearerToken(){
        String keys = Constant.consumerKey + ":"+ Constant.consumerSecret;

        String requestToken = Base64.encodeBase64String(keys.getBytes());

        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create("",null);

        Request request = new Request.Builder()
                .url("https://api.twitter.com/oauth2/token?grant_type=client_credentials")
                .post(requestBody)
                .addHeader("Authorization", "Basic " + requestToken)
                .build();

        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        BearerToken bearerToken = null;

            try {
                bearerToken = objectMapper.readValue(response.body().string(), new TypeReference<BearerToken>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }

        return bearerToken.getAccessToken();
    }
}
