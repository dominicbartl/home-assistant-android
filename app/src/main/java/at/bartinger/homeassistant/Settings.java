package at.bartinger.homeassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {

    private static Settings instance;

    private final SharedPreferences prefs;

    private Settings(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context);
        }
        return instance;
    }

    public String getServerURL() {
        return prefs.getString("server_url", "http://192.168.1.38:3000");
    }

    public void setServerURL(String url) {
        prefs.edit().putString("server_url", url).apply();
    }

}
