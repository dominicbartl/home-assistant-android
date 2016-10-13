package at.bartinger.homeassistant.service;

import java.util.List;

import at.bartinger.homeassistant.model.Device;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DeviceSerivce {

    @GET("devices")
    Call<List<Device>> listDevices();

    @POST("devices")
    Call<List<Device>> createDevice(@Body Device device);




}
