package at.bartinger.homeassistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.net.URISyntaxException;
import java.util.List;

import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.repository.DeviceRepository;
import at.bartinger.homeassistant.service.ApiService;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        try {
            ApiService.init(this);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, DevicesFragment.newInstance())
                .commit();

    }

}
