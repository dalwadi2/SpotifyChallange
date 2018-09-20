package io.github.dalwadi2.spotifychallange.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import io.github.dalwadi2.spotifychallange.R;
import io.github.dalwadi2.spotifychallange.adapters.SongsAdapter;
import io.github.dalwadi2.spotifychallange.network.ApiClient;
import io.github.dalwadi2.spotifychallange.network.ApiInterface;
import io.github.dalwadi2.spotifychallange.network.response.RespPlaylist;
import io.github.dalwadi2.spotifychallange.utils.AppConfig;
import io.github.dalwadi2.spotifychallange.utils.DividerItemDecoration;
import io.github.dalwadi2.spotifychallange.utils.PreferenceHelper;
import io.github.dalwadi2.spotifychallange.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListActivity extends AppCompatActivity {
    private static final String TAG = SongListActivity.class.getSimpleName();
    private static final String CLIENT_ID = "f9ef6158480e42ecbe40ab77af68778f";
    private static final String REDIRECT_URI = "http://www.apptechasia.com/";
    protected ApiInterface apiService;
    protected PreferenceHelper helper;
    Toolbar toolbar;
    private ProgressBar pb;
    private RecyclerView rvSongs;
    private Call<RespPlaylist> playlistCall;
    private AppCompatActivity mActivity;
    private SpotifyAppRemote mSpotifyAppRemote;
    private String trackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pb = findViewById(R.id.pb);
        rvSongs = findViewById(R.id.rv_songs);

        mActivity = this;
        helper = new PreferenceHelper(this, AppConfig.PREF.PREF_FILE);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        loadData();
    }

    private void loadData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rvSongs.setLayoutManager(layoutManager);
        rvSongs.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mActivity, R.drawable.divider_recyclerview,
                false, false);
        rvSongs.addItemDecoration(dividerItemDecoration);

        SongsAdapter songsAdapter = new SongsAdapter(mActivity);
        rvSongs.setAdapter(songsAdapter);
        songsAdapter.SetOnItemClickListener(new SongsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, RespPlaylist.Items model) {
                trackId = model.getTrack().getUri();
                ConnectionParams connectionParams =
                        new ConnectionParams.Builder(CLIENT_ID)
                                .setRedirectUri(REDIRECT_URI)
                                .showAuthView(true)
                                .build();
                SpotifyAppRemote.CONNECTOR.connect(mActivity, connectionParams,
                        new Connector.ConnectionListener() {

                            @Override
                            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                                mSpotifyAppRemote = spotifyAppRemote;
                                Log.e(TAG, "Connected! ");

                                // Now you can start interacting with App Remote
                                connected();
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                Log.e("MainActivity", throwable.getMessage(), throwable);

                            }
                        });
            }
        });
        pb.setVisibility(View.VISIBLE);
        playlistCall = apiService.PLAYLIST_CALL("Bearer " + helper.LoadStringPref(AppConfig.PREF.AUTH_TOKEN, "")
                , AppConfig.EXTRA.playlist_id);

        playlistCall.enqueue(new Callback<RespPlaylist>() {
            @Override
            public void onResponse(@NonNull Call<RespPlaylist> call, @NonNull Response<RespPlaylist> response) {
                pb.setVisibility(View.GONE);
                if (response.code() == 200) {
                    songsAdapter.updateList(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RespPlaylist> call, @NonNull Throwable t) {
                Utils.Log(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void connected() {
        mSpotifyAppRemote.getPlayerApi().play("spotify:user:spotify:playlist:" + AppConfig.EXTRA.playlist_id);

        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState().setEventCallback(new Subscription.EventCallback<PlayerState>() {

            public void onEvent(PlayerState playerState) {
                final Track track = playerState.track;
                if (track != null) {
                    Log.e(TAG, track.name + " by " + track.artist.name);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.CONNECTOR.disconnect(mSpotifyAppRemote);
    }
}
