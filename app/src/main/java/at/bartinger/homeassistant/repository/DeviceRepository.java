package at.bartinger.homeassistant.repository;

import at.bartinger.homeassistant.model.Device;

public class DeviceRepository extends BaseRepository<Device> {

    private static DeviceRepository instance;

    public DeviceRepository() {
        super(Device.class);
    }

    public static DeviceRepository getInstance() {
        if (instance == null) {
            instance = new DeviceRepository();
        }
        return instance;
    }

}
