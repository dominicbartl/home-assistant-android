package at.bartinger.homeassistant.repository;

import java.util.List;

import at.bartinger.homeassistant.model.Device;
import io.realm.Realm;

public class DeviceRepository {

    private Realm realm;

    public DeviceRepository() {
        // Obtain a Realm instance
        this.realm = Realm.getDefaultInstance();
    }

    public Device create(Device device) {
        realm.beginTransaction();
        Device model = realm.copyToRealm(device);
        realm.commitTransaction();
        return model;
    }

    public List<Device> list() {
        return realm.where(Device.class).findAll();
    }

}
