package at.bartinger.homeassistant.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import at.bartinger.homeassistant.R;
import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.repository.DeviceRepository;
import at.bartinger.homeassistant.service.ApiService;
import at.bartinger.homeassistant.service.DeviceService;
import at.bartinger.homeassistant.service.SocketService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceListFragment extends Fragment implements SocketService.Listener, DeviceAdapter.Listener {

    private DeviceAdapter adapter;

    private DeviceRepository repository;
    private LinearLayoutManager layoutManager;

    public static DeviceListFragment newInstance() {
        Bundle args = new Bundle();
        DeviceListFragment fragment = new DeviceListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_device_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter = new DeviceAdapter(this));

        repository = new DeviceRepository();

        ApiService.connectSocket(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ApiService.disconnect();
    }

    @Override
    public void onDeviceReceived(Device device) {
        adapter.add(0, device);
        adapter.notifyItemInserted(0);
        layoutManager.scrollToPosition(0);
    }

    @Override
    public void onDeviceClick(final Device device) {
        DeviceNameDialog nameDialog = DeviceNameDialog.newInstance();
        nameDialog.setListener(new DeviceNameDialog.Listener() {
            @Override
            public void onNameEntered(String name) {
                device.setName(name);
                createDevice(device);

            }
        });
        nameDialog.show(getChildFragmentManager(), "device_name");
    }

    private void createDevice(Device device) {
        final ProgressDialog loading = ProgressDialog.show(getContext(), null, "Saving device " + device.getName());
        ApiService.create(DeviceService.class).createDevice(device).enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                loading.dismiss();
                if (!response.isSuccessful()) {
                    return;
                }
                repository.create(response.body());
                getFragmentManager().popBackStack();
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                loading.dismiss();
            }
        });

    }
}
