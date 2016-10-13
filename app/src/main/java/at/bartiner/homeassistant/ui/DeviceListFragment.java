package at.bartiner.homeassistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URISyntaxException;

import at.bartiner.homeassistant.R;
import at.bartiner.homeassistant.model.Device;
import at.bartiner.homeassistant.repository.DeviceRepository;
import at.bartiner.homeassistant.service.SocketService;

public class DeviceListFragment extends Fragment implements SocketService.Listener, DeviceAdapter.Listener {

    private SocketService service;
    private DeviceAdapter adapter;

    private DeviceRepository repository;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter = new DeviceAdapter(this));

        repository = new DeviceRepository();

        try {
            service = new SocketService("http://10.0.2.2:3000", this);
            service.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDeviceReceived(Device device) {
        adapter.add(device);
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
    }

    @Override
    public void onDeviceClick(final Device device) {
        DeviceNameDialog nameDialog = DeviceNameDialog.newInstance();
        nameDialog.setListener(new DeviceNameDialog.Listener() {
            @Override
            public void onNameEntered(String name) {
                device.setName(name);
                repository.create(device);
            }
        });
        nameDialog.show(getChildFragmentManager(), "device_name");
    }
}
