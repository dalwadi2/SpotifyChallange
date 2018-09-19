package io.github.dalwadi2.spotifychallange.network.response;

import com.google.gson.annotations.SerializedName;

public class VideoToken {


    /**
     * error : false
     * message : success
     * data : {"identity":"","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTSzFkNGVmZDM0MGY5NzQ5MWI2YmRjYTY4Yzc1MzBhY2JhLTE1MTA3MzgzNDQiLCJpc3MiOiJTSzFkNGVmZDM0MGY5NzQ5MWI2YmRjYTY4Yzc1MzBhY2JhIiwic3ViIjoiQUNjYjNlMzJhNTNkMWVlNzJjMzNhYjgxMTczOTEzNGVjMyIsImV4cCI6MTUxMDc0MTk0NCwiZ3JhbnRzIjp7InZpZGVvIjp7fX19.rA6vpumTZpfRjwdXbWmq-RBLx7cCP8GLuJEvA5E4bxE"}
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataEntity data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * identity :
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTSzFkNGVmZDM0MGY5NzQ5MWI2YmRjYTY4Yzc1MzBhY2JhLTE1MTA3MzgzNDQiLCJpc3MiOiJTSzFkNGVmZDM0MGY5NzQ5MWI2YmRjYTY4Yzc1MzBhY2JhIiwic3ViIjoiQUNjYjNlMzJhNTNkMWVlNzJjMzNhYjgxMTczOTEzNGVjMyIsImV4cCI6MTUxMDc0MTk0NCwiZ3JhbnRzIjp7InZpZGVvIjp7fX19.rA6vpumTZpfRjwdXbWmq-RBLx7cCP8GLuJEvA5E4bxE
         */

        @SerializedName("identity")
        private String identity;
        @SerializedName("token")
        private String token;

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
