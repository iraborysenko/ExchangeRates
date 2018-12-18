package com.borysenko.exchangerates.dagger;


import com.borysenko.exchangerates.model.ChartData;
import com.borysenko.exchangerates.model.ExchangeRates;
import com.borysenko.exchangerates.retrofit.ApiInterface;
import com.borysenko.exchangerates.retrofit.ChartDeserializer;
import com.borysenko.exchangerates.retrofit.RatesDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.borysenko.exchangerates.retrofit.ApiInterface.PB_URL;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 15:22
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ExchangeRates.class, new RatesDeserializer())
                .registerTypeAdapter(ChartData.class, new ChartDeserializer())
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(100, TimeUnit.SECONDS);
        client.readTimeout(55, TimeUnit.SECONDS);
        client.writeTimeout(55, TimeUnit.SECONDS);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(PB_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
