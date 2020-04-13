package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class PostLogin {
    @SerializedName("success")
    private UserResponse user;

    public UserResponse getUser() {
        return user;
    }
}
