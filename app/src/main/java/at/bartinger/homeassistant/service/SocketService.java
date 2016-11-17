package at.bartinger.homeassistant.service;

import android.os.Handler;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.net.URISyntaxException;

import at.bartinger.homeassistant.model.Device;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketService {

    private final Handler handler = new Handler();

    private final Gson gson;
    private Socket socket;
    private Listener listener;

    public SocketService(Socket socket, final Gson gson) throws URISyntaxException {
        this.gson = gson;
        this.socket = socket;
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(SocketService.class.getName(), "Socket connected");
            }
        });
        socket.on("device", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(SocketService.class.getName(), "Got a message");
                if (listener != null && args.length > 0 && args[0] instanceof JSONObject) {
                    JSONObject device = (JSONObject) args[0];
                    final Device device1 = gson.fromJson(device.toString(), Device.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.onDeviceReceived(device1);
                            }
                        }
                    });
                }
            }
        });
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(SocketService.class.getName(), "Socket connected");
            }
        });
        socket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(SocketService.class.getName(), "Error");
            }
        });
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.e(SocketService.class.getName(), "Connect error");
            }
        });
    }

    public void connect(Listener listener) {
        this.listener = listener;
        socket.connect();
    }

    public void disconnect() {
        this.listener = null;
        socket.disconnect();
    }

    public interface Listener {

        void onDeviceReceived(Device device);

    }
}
