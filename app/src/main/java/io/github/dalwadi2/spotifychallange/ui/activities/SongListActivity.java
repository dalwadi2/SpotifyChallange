package io.github.dalwadi2.spotifychallange.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.dalwadi2.spotifychallange.R;
import io.github.dalwadi2.spotifychallange.network.ApiInterface;
import io.github.dalwadi2.spotifychallange.utils.PreferenceHelper;

public class SongListActivity extends AppCompatActivity {

    protected ApiInterface apiService;
    protected PreferenceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_song_list);

//        setSupportActionBar(toolbar);

    }

}
