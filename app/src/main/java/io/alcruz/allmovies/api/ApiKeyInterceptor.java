package io.alcruz.allmovies.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    private static final String API_KEY_QUERY_PARAM = "api_key";

    private String mApiKey;

    public ApiKeyInterceptor(String apiKey) {
        this.mApiKey = apiKey;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl newUrl = chain.request()
                .url().newBuilder()
                .addQueryParameter(API_KEY_QUERY_PARAM, mApiKey).build();

        return chain.proceed(request.newBuilder().url(newUrl).build());
    }
}
