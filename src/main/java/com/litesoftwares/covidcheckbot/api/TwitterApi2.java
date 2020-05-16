package com.litesoftwares.covidcheckbot.api;

import com.litesoftwares.covidcheckbot.api.exception.TwitterApiException;
import com.litesoftwares.covidcheckbot.api.utils.Utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class TwitterApi2 {
    private final String API_URL = "https://api.twitter.com/";

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient())
            .addConverterFactory(JacksonConverterFactory.create());

    private Retrofit retrofit =  builder.build();

    private OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                       @NotNull
                       @Override
                       public okhttp3.Response intercept(Chain chain) throws IOException {
                           Request request = chain.request().newBuilder()
                                   .addHeader("Authorization", "Bearer " + Utils.getBearerToken()).build();
                           return chain.proceed(request);
                       }})
                .build();
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
                    TwitterApiError apiError = getTwitterApiError(response);
                    apiError.setCode(response.code());
                    apiError.setMessage(response.message());
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
