package io.github.dalwadi2.spotifychallange.network;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.github.dalwadi2.spotifychallange.BuildConfig;
import io.github.dalwadi2.spotifychallange.utils.AppConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String TAG = ApiClient.class.getSimpleName();
    private static Retrofit retrofit = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
        if (BuildConfig.DEBUG)
            Log.i(TAG, message);
    }).setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient okHttpClient1 = new OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
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
