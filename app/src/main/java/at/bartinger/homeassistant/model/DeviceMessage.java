package at.bartinger.homeassistant.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DeviceMessage extends RealmObject {

    @PrimaryKey
    private int _id;

    private String id;
    private String unit;
    private String state;

    public DeviceMessage() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
