package at.bartinger.homeassistant.service;

import android.content.Context;

import java.net.URISyntaxException;

import at.bartinger.homeassistant.Settings;
import retrofit2.Retrofit;

public class ApiService {

    private static Retrofit retrofit;
    private static SocketService socketService;

    public static void init(Context context) throws URISyntaxException {
        String url = Settings.getInstance(context).getServerURL();
        socketService = new SocketService(url);
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
    }

    public static <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static void connectSocket(SocketService.Listener listener) {
        socketService.connect(listener);
    }

    public static void disconnect() {
        socketService.disconnect();
    }

}
