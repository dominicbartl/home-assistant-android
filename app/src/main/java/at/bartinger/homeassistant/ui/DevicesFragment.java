package at.bartinger.homeassistant.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevicesFragment extends Fragment implements DeviceGridAdapter.Listener {

    private DeviceGridAdapter adapter;
    private View emptyView;
    private DeviceRepository repository;
    private Listener listener;
    private DeviceService service;

    public static DevicesFragment newInstance() {
        return new DevicesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_devices, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter = new DeviceGridAdapter());
        adapter.setListener(this);
        emptyView = view.findViewById(android.R.id.empty);

        view.findViewById(R.id._fab_frag_devices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddClick();
            }
        });

        repository = new DeviceRepository();
        service = ApiService.create(DeviceService.class);
        service.listDevices().enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                repository.save(response.body());
                updateItems();
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    private void updateItems() {
        adapter.clear();
        adapter.addAll(repository.list());
        adapter.notifyDataSetChanged();
        emptyView.setVisibility(adapter.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateItems();
    }

    @Override
    public void onDeviceActionClick(Device device, String action) {

    }

    public interface Listener {

        void onAddClick();

    }
}
