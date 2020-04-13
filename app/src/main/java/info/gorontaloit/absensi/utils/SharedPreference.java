package info.gorontaloit.absensi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreference {
    public static final String USERNAME = "USERNAME";
    public static final String TOKEN = "TOKEN";
    public static final String NIP = "NIP";

    private final SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        pref = context.getSharedPreferences(USERNAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void saveString(String key, String val) {
        editor.putString(key, val);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(USERNAME,"");
    }

    public String getToken() {
        return pref.getString(TOKEN, "");
    }

    public String getNip() {
        return pref.getString(NIP, "");
    }

    public boolean isLoggedIn() {
        if (getToken() != null) {
            Log.d("GetToken", getToken());
            return true;
        }
        return false;
    }

    public void clearLoggedInUser() {
        editor.remove(USERNAME);
        editor.remove(TOKEN);
        editor.remove(NIP);
        editor.commit();

    }
}
