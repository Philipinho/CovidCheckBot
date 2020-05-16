package com.litesoftwares.covidcheckbot.corona_api;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(5, TimeUnit.MINUTES) // 15 minutes cache
                .build();

        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
