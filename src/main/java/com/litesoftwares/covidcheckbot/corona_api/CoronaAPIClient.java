package com.litesoftwares.covidcheckbot.corona_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litesoftwares.covidcheckbot.corona_api.model.Country;
import com.litesoftwares.covidcheckbot.corona_api.model.TotalStats;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoronaAPIClient {
    private final String API_URL = "https://disease.sh/v2";
    private OkHttpClient httpClient = new OkHttpClient();

    private String cacheDir = "src/main/resources";
    private File httpCacheDirectory = new File(cacheDir, "http-cache");
    private int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new CacheInterceptor())
            .cache(cache)
            .build();


    public TotalStats getTotalStats(){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = getResponse(API_URL + "/all");

        TotalStats totalStats = null;

        try {
             totalStats = objectMapper.readValue(jsonString, new TypeReference<TotalStats>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return totalStats;
    }

    public Country getCountry(String countryName){
        String response = getResponse(API_URL + "/countries/" + countryName);

        if (response == null){
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Country country = new Country();

        try {
            country = objectMapper.readValue(response, new TypeReference<Country>() {});
        }catch (Exception e){
            e.printStackTrace();
        }

        return country;
    }

    public List<Country> getAllCountries(){
        String response = getResponse(API_URL + "/countries");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Country> countryList = new ArrayList<>();

        try {
            countryList = objectMapper.readValue(response, new TypeReference<List<Country>>() {});
        }catch (Exception e){
            e.printStackTrace();
        }

        return countryList;
    }

    private String getResponse(String api){

        Request request = new Request.Builder()
                .url(api).build();

        String response = null;
        Response apiResponse = null;
        try {
             apiResponse = okHttpClient.newCall(request).execute();
            if (apiResponse.code() == 200){
                response = apiResponse.body().string();
            }
            apiResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return response;
    }
}
