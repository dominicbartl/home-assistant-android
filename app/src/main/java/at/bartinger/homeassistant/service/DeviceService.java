package at.bartinger.homeassistant.service;

import java.util.List;

import at.bartinger.homeassistant.model.Device;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeviceService {

    @GET("devices")
    Call<List<Device>> listDevices();

    @POST("devices")
    Call<Device> createDevice(@Body Device device);

    @PATCH("devices/{id}/control")
    Call<Void> controlDevice(@Path("id") String id, @Query("status") String status);




}
