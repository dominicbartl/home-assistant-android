package at.bartiner.homeassistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.net.URISyntaxException;

import at.bartiner.homeassistant.model.Device;
import at.bartiner.homeassistant.service.SocketService;

public class MainActivity extends AppCompatActivity implements SocketService.Listener {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, DeviceListFragment.newInstance())
                .commit();

    }

    @Override
    public void onDeviceReceived(Device device) {

    }
}
