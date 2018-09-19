package io.github.dalwadi2.spotifychallange.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import io.github.dalwadi2.spotifychallange.R;
import io.github.dalwadi2.spotifychallange.network.ApiClient;
import io.github.dalwadi2.spotifychallange.network.ApiInterface;
import io.github.dalwadi2.spotifychallange.utils.AppConfig;
import io.github.dalwadi2.spotifychallange.utils.PreferenceHelper;
import io.github.dalwadi2.spotifychallange.utils.Utils;


public class AuthActivity extends AppCompatActivity {
    public static final int AUTH_TOKEN_REQUEST_CODE = 0x10;
    public static final int AUTH_CODE_REQUEST_CODE = 0x11;
    private static final String TAG = AuthActivity.class.getSimpleName();
    private static final String CLIENT_ID = "f9ef6158480e42ecbe40ab77af68778f";
    private static final String REDIRECT_URI = "http://www.apptechasia.com/";
    protected ApiInterface apiService;
    protected PreferenceHelper helper;
    Toolbar toolbar;
    private String mAccessToken;
    private String mAccessCode;
    private int expiresIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new PreferenceHelper(this, AppConfig.PREF.PREF_FILE);
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    protected void onDestroy() {
//        cancelCall();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onRequestCodeClicked(View view) {
        final AuthenticationRequest request = getAuthenticationRequest(AuthenticationResponse.Type.CODE);
        AuthenticationClient.openLoginActivity(this, AUTH_CODE_REQUEST_CODE, request);
    }

    public void onRequestTokenClicked(View view) {
        final AuthenticationRequest request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN);
        AuthenticationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request);
    }

    private AuthenticationRequest getAuthenticationRequest(AuthenticationResponse.Type type) {
        return new AuthenticationRequest.Builder(CLIENT_ID, type, REDIRECT_URI)
                .setShowDialog(false)
                .setScopes(new String[]{"user-read-email"})
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            mAccessToken = response.getAccessToken();
            if (TextUtils.isEmpty(mAccessToken)) {
                Toast.makeText(this, "Auth Fail.", Toast.LENGTH_SHORT).show();
                return;
            }
            expiresIn = response.getExpiresIn();
            helper.initPref();
            helper.SaveStringPref(AppConfig.PREF.AUTH_TOKEN, mAccessToken);
            helper.ApplyPref();
            startActivity(new Intent(AuthActivity.this, SongListActivity.class));
            finish();
            Utils.Log(TAG, "onActivityResult: " + mAccessToken);
        } else if (AUTH_CODE_REQUEST_CODE == requestCode) {
            mAccessCode = response.getCode();
        }
    }

}
