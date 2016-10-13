package at.bartiner.homeassistant.model;

public class Device {

    private String uuid;
    private String origin;
    private String protocol;
    private int repeats;

    private DeviceMessage message;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public DeviceMessage getMessage() {
        return message;
    }

    public void setMessage(DeviceMessage message) {
        this.message = message;
    }
}
