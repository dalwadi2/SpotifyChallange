package io.github.dalwadi2.spotifychallange.utils;


public class AppConfig {

    public static final class URL {
        public static final String BASE_URL = "https://api.spotify.com/v1/";
        //        public static final String API_PLAYLIST = "artists/";
        public static final String API_PLAYLIST = "playlists/";
        public static final String POST_API = "/tracks";

    }

    public static final class PREF {
        public static final String PREF_FILE = "Configs";
        public static final String AUTH_TOKEN = "auth_token";
        public static final String AUTH_TIMESTAMP = "auth_timestamp";
    }


    public static final class EXTRA {
        public static final String playlist_id = "37i9dQZF1DX2sUQwD7tbmL";
    }

    public static final class PARAMS {
        public static final String Authorization = "Authorization";
        public static final String playlist_id = "playlist_id";
    }

}
