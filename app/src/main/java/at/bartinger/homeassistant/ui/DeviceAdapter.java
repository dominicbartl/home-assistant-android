package at.bartinger.homeassistant.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import at.bartinger.homeassistant.R;
import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.ui.core.GenericArrayAdapter;


class DeviceAdapter extends GenericArrayAdapter<DeviceAdapter.ViewHolder, Device> {

    private Listener listener;

    DeviceAdapter(Listener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.item_device, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView text1;
        private TextView text2;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
        }

        void bind(Device device) {
            text1.setText(device.getProtocol());
            text2.setText(device.getUuid());
        }

        @Override
        public void onClick(View view) {
            listener.onDeviceClick(getItem(getAdapterPosition()));
        }
    }

    interface Listener {

        void onDeviceClick(Device device);

    }

}
