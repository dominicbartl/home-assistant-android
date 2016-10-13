package at.bartinger.homeassistant.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import at.bartinger.homeassistant.R;
import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.ui.core.GenericArrayAdapter;

public class DeviceGridAdapter extends GenericArrayAdapter<DeviceGridAdapter.ViewHolder, Device> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.item_configured_device, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text1;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }

        void bind(Device device) {
            text1.setText(device.getName());
        }
    }


}
