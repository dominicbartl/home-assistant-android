package at.bartinger.homeassistant.service;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;

import at.bartinger.homeassistant.Settings;
import io.socket.client.IO;
import io.socket.client.Socket;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static Retrofit retrofit;
    private static SocketService socketService;

    public static void init(Context context) throws URISyntaxException {
        String url = Settings.getInstance(context).getServerURL();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Socket socket = IO.socket(url);
        socketService = new SocketService(socket, gson);
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
