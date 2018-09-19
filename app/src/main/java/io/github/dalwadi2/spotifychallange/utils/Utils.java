package io.github.dalwadi2.spotifychallange.utils;

import android.app.Activity;
import android.util.Log;

import io.github.dalwadi2.spotifychallange.BuildConfig;


public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    private Activity activity;

    public Utils(Activity activity) {
        this.activity = activity;
    }

    public static void Log(String TAG, String msg) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, msg);
    }

}
