package com.piotrkalitka.reposwatcher.api;

import com.google.gson.GsonBuilder;
import com.piotrkalitka.reposwatcher.util.Constants;

import java.lang.reflect.Modifier;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static ApiProvider apiProvider;
    private final Api api;

    private ApiProvider() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor contentType = chain -> {
            Request request = chain.request().newBuilder().addHeader("Content-type", "application/x-www-urlencoded").build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(httpLoggingInterceptor);
        clientBuilder.addInterceptor(contentType);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                        .create()
                )).baseUrl(Constants.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();

        api = retrofit.create(Api.class);
    }

    private Api getApi() {
        return api;
    }

    public static Api provideApi() {
        if (apiProvider == null) apiProvider = new ApiProvider();
        return apiProvider.getApi();
    }

}