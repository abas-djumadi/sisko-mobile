package info.gorontaloit.absensi.data.model;

public class User {
    private String username;
    private String nip;
    private String token;

    public User() {
    }

    public User(String username, String nip, String token) {
        this.username = username;
        this.nip = nip;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getNip() {
        return nip;
    }

    public String getToken() {
        return token;
    }
}
