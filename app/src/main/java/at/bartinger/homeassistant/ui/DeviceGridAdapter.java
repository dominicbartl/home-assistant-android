package at.bartinger.homeassistant.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.bartinger.homeassistant.R;
import at.bartinger.homeassistant.model.Device;
import at.bartinger.homeassistant.ui.core.GenericArrayAdapter;

public class DeviceGridAdapter extends GenericArrayAdapter<DeviceGridAdapter.ViewHolder, Device> {

    private Listener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.item_configured_device, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView text1;
        private Button btnNegative;
        private Button btnPositive;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            btnNegative = (Button) itemView.findViewById(R.id.btn_item_configured_device_negative);
            btnPositive = (Button) itemView.findViewById(R.id.btn_item_configured_device_positive);

            btnNegative.setOnClickListener(this);
            btnPositive.setOnClickListener(this);
        }

        void bind(Device device) {
            text1.setText(device.getName());
        }

        @Override
        public void onClick(View view) {
            if (listener == null) {
                return;
            }
            Device device = getItem(getAdapterPosition());

            switch (view.getId()) {

                case R.id.btn_item_configured_device_negative:
                    listener.onDeviceActionClick(device, "off");
                    break;

                case R.id.btn_item_configured_device_positive:
                    listener.onDeviceActionClick(device, "on");
                    break;

            }

        }
    }

    public interface Listener {

        void onDeviceActionClick(Device device, String action);

    }


}
