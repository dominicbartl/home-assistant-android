package at.bartinger.homeassistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.bartinger.homeassistant.R;
import at.bartinger.homeassistant.repository.DeviceRepository;

public class DevicesFragment extends Fragment {

    private DeviceGridAdapter adapter;

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

        DeviceRepository repository = new DeviceRepository();
        adapter.addAll(repository.list());
        adapter.notifyDataSetChanged();
    }
}
