package io.github.dalwadi2.spotifychallange.utils;


public class AppConfig {

    public static final class URL {
        public static final String BASE_URL = "https://api.spotify.com/v1/";
        public static final String API_PLAYLIST = "artists";
        public static final String POST_API = "/top-tracks?country=US";

    }

    public static final class PREF {
        public static final String PREF_FILE = "Configs";
        public static final String USER_LOGIN = "login";
        public static final String AUTH_TOKEN = "auth_token";
        public static final String AUTH_TIMESTAMP = "auth_timestamp";
    }

    public static final class BUNDLE {
        public static final String FROM_APP = "from_app";
    }

    public static final class EXTRA {
        public static final String FB_CHATS = "all_chat_users";
        public static final int NOTIFICATION_REMINDER = 104;
    }

    public static final class PARAMS {
        public static final String Authorization = "Authorization";
        public static final String artist_id = "artist_id";
    }

}
