package at.bartinger.homeassistant.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Device extends RealmObject {

    @PrimaryKey
    private String uuid;
    private String name;
    private String origin;
    private String protocol;
    private int repeats;

    // Device
    private String id;
    private String unit;
    private String state;

    public Device() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

}
