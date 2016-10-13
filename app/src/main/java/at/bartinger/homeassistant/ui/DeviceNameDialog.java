package at.bartinger.homeassistant.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import at.bartinger.homeassistant.R;

public class DeviceNameDialog extends DialogFragment {

    private EditText editName;
    private Listener listener;

    public static DeviceNameDialog newInstance() {
        return new DeviceNameDialog();
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editName = (EditText) view.findViewById(R.id.edit_dialog_device_name);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.device_name)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (listener != null) {
                                    listener.onNameEntered(editName.getText().toString());
                                }
                                dismiss();
                            }
                        }
                )
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(true);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_device_name, null);
        onViewCreated(view, null);
        builder.setView(view);


        return builder.create();
    }

    interface Listener {
        void onNameEntered(String name);
    }
}
