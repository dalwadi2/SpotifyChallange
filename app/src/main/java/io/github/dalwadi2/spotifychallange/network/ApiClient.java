package io.github.dalwadi2.spotifychallange.network;


import java.util.concurrent.TimeUnit;

import io.github.dalwadi2.spotifychallange.utils.AppConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient1 = new OkHttpClient().newBuilder()
//            .addNetworkInterceptor(new StethoInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient1)
                    .build();
        }
        return retrofit;
    }
}
