package at.bartiner.homeassistant.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import at.bartiner.homeassistant.R;
import at.bartiner.homeassistant.model.Device;
import at.bartiner.homeassistant.ui.core.GenericArrayAdapter;


public class DeviceAdapter extends GenericArrayAdapter<DeviceAdapter.ViewHolder, Device> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.item_device, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text1;
        private TextView text2;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
        }

        void bind(Device device) {
            text1.setText(device.getProtocol());
            text2.setText(device.getUuid());
        }
    }

}
