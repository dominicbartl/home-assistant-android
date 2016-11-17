package at.bartinger.homeassistant.repository;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

public class  BaseRepository<T extends RealmObject> {

    private Realm realm;
    private Class<T> tClass;

    private Listener<T> listener;

    public BaseRepository(Class<T> tClass) {
        this.tClass = tClass;
        this.realm = Realm.getDefaultInstance();
    }

    public T create(T device) {
        realm.beginTransaction();
        T model = realm.copyToRealm(device);
        realm.commitTransaction();
        if (listener != null) {
            listener.onModelCreated(model);
        }
        return model;
    }

    public List<T> save(List<T> models) {
        realm.beginTransaction();
        List<T> ts = realm.copyToRealmOrUpdate(models);
        realm.commitTransaction();
        return ts;
    }

    public List<T> list() {
        return realm.where(tClass).findAll();
    }

    public Listener<T> getListener() {
        return listener;
    }

    public void setListener(Listener<T> listener) {
        this.listener = listener;
    }

    interface Listener<T> {

        void onModelCreated(T model);

    }
}
