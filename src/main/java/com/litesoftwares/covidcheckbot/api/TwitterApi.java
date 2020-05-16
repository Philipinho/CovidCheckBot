package com.litesoftwares.covidcheckbot.api;

import com.litesoftwares.covidcheckbot.api.exception.TwitterApiException;
import com.litesoftwares.covidcheckbot.api.utils.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class TwitterApi {
    private final String API_URL = "https://api.twitter.com/";

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient())
            .addConverterFactory(JacksonConverterFactory.create());

    private Retrofit retrofit =  builder.build();

    private OkHttpClient okHttpClient(){
       // HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(getConsumer()))
            //    .addInterceptor(loggingInterceptor)
                .build();
    }

    private OkHttpOAuthConsumer getConsumer() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constant.consumerKey, Constant.consumerSecret);
        consumer.setTokenWithSecret(Constant.accessToken, Constant.accessTokenSecret);
        return consumer;
    }

    public <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

    public <T> T executeSync(Call<T> call){
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                try {
                    TwitterApiError apiError = getTwitterApiError(response);;
                    throw new TwitterApiException(apiError);
                } catch (IOException e) {
                    throw new TwitterApiException(response.toString(), e);
                }
            }
        } catch (IOException e) {
            throw new TwitterApiException(e);
        }
    }

    private TwitterApiError getTwitterApiError(Response<?> response) throws IOException {
        return (TwitterApiError) retrofit.responseBodyConverter(TwitterApiError.class, new Annotation[0])
                .convert(response.errorBody());
    }
}
