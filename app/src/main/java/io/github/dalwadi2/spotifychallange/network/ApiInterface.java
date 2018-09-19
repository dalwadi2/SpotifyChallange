package io.github.dalwadi2.spotifychallange.network;


import io.github.dalwadi2.spotifychallange.network.response.RespPaylist;
import io.github.dalwadi2.spotifychallange.utils.AppConfig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET(AppConfig.URL.API_PLAYLIST + "{" + AppConfig.PARAMS.artist_id + "}" + AppConfig.URL.POST_API)
    Call<RespPaylist> PLAYLIST_CALL(@Header(AppConfig.PARAMS.Authorization) String header
            , @Path(AppConfig.PARAMS.artist_id) String artist_id);
}
