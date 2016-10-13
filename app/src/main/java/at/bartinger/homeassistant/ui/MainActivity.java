package at.bartinger.homeassistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.repository.DeviceRepository;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);

        DeviceRepository repository = new DeviceRepository();
        List<Device> list = repository.list();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, DeviceListFragment.newInstance())
                .commit();

    }

}
