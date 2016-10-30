package pl.projektorion.krzysztof.blesensortag.fragments;


import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import pl.projektorion.krzysztof.blesensortag.R;
import pl.projektorion.krzysztof.blesensortag.constants.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class BLeServiceScannerFragment extends Fragment {

    public final static String EXTRA_BLE_DEVICE =
            "pl.projektorion.krzysztof.blesensortag.bleservicescannerfragment.extra.BLE_DEVICE";

    private View view;
    private Context context;
    private Context appContext;
    private BluetoothDevice bleDevice;
    private TextView labelDeviceName;
    private TextView labelDeviceAddress;
    private ExpandableListView deviceServicesDisplayWidget;


    public BLeServiceScannerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ble_service_scanner, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = getActivity().getApplicationContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        retrieve_incoming_data();
        assert_ble_device_exists();
        init_android_framework();
        init_widgets();
        init_label_values();
    }

    public static BLeServiceScannerFragment newInstance(BluetoothDevice device) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_BLE_DEVICE, device);
        BLeServiceScannerFragment fragment = new BLeServiceScannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void retrieve_incoming_data()
    {
        Bundle bundle = getArguments();
        bleDevice = bundle.getParcelable(EXTRA_BLE_DEVICE);
    }

    private void assert_ble_device_exists()
    {
        if( bleDevice == null ) {
            final Activity activityContext = getActivity();
            activityContext.finish();
            Toast.makeText(appContext,
                    R.string.toast_ble_service_scan_device_not_passed,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void init_android_framework()
    {
        try {
            this.context = this.view.getContext();
        } catch (NullPointerException e) {
            Log.d(Constant.BLESERV_ERR_TAG, Constant.CONTEXT_ERR);
        }
    }

    private void init_widgets()
    {
        labelDeviceAddress = (TextView) view.findViewById(R.id.label_current_device_address);
        labelDeviceName = (TextView) view.findViewById(R.id.label_current_device_name);
        deviceServicesDisplayWidget = (ExpandableListView)
                view.findViewById(R.id.expandablelistview_device_services);
    }

    private void init_label_values()
    {
        labelDeviceName.setText(bleDevice.getName());
        labelDeviceAddress.setText(bleDevice.getAddress());
    }
}
