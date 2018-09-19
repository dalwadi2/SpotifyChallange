package io.github.dalwadi2.spotifychallange.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress {

    private ProgressDialog pDialog;
    private Context activity;

    public Progress(Context activity) {
        this.activity = activity;
    }

    public void createDialog(boolean cancelable) {
        pDialog = new ProgressDialog(activity);
        pDialog.setCancelable(cancelable);

    }

    public void DialogMessage(String message) {
        pDialog.setMessage(message);
    }

    public void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
