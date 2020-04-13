package info.gorontaloit.absensi.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("name")
    private String username;

    @SerializedName("nip")
    private String nip;

    public UserResponse(String token, String username, String nip) {
        this.token = token;
        this.username = username;
        this.nip = nip;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getNip() {
        return nip;
    }
}
