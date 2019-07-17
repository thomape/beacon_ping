package com.example.beaconping;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.HashMap;

public class BluetoothFragment extends Fragment {
    private static final int REQUEST_LOCATION = 123;
    private ProximityManager proximityManager;
    private HashMap<Integer, Beacon> beacons = new HashMap<Integer, Beacon>();
    private Beacon beacon;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        proximityManager = ProximityManagerFactory.create(getContext());
        proximityManager.setIBeaconListener(createIBeaconListener());

        View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        Button scanButton = (Button) view.findViewById(R.id.scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanning();
            }
        });

        Button stopButton = (Button) view.findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });


        if(beacons.size() > 0){

            recyclerView = (RecyclerView) view.findViewById(R.id.beacon_list);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            for (int i= 0; i < 100; i++) {
                beacons.put(beacon.getmMinorId(), beacon);

            }
            mAdapter = new BluetoothAdapter(beacons);
            recyclerView.setAdapter(mAdapter);
        }





        return view;
    }

    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_LOCATION);
        } else {

            System.out.println("Location permissions available, starting location");
        }
    }

    private IBeaconListener createIBeaconListener() {
        return new SimpleIBeaconListener() {
            @Override
            public void onIBeaconDiscovered(IBeaconDevice ibeacon, IBeaconRegion region) {
                Log.i("Sample", "IBeacon discovered: " + ibeacon.toString());

                beacon = new Beacon(ibeacon.getMinor(),ibeacon.getRssi(),ibeacon.getDistance(), ibeacon.getUniqueId());
                // Hashmap attempt at keeping the beacons updated
                if(beacons.containsKey(beacon.getmMinorId())) {
                    beacons.put(beacon.getmMinorId(), beacon);
                }else {
                    beacons.put(beacon.getmMinorId(), beacon);
                }
                Log.i("SIZE", "IBeacon size: " + beacons.size());
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        checkPermissions();
    }

    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }

    @Override
    public void onStop() {
        proximityManager.stopScanning();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        proximityManager.disconnect();
        proximityManager = null;
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode == REQUEST_LOCATION) {

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                System.out.println("Location permissions granted, starting location");
                startScanning();
            }
        }
    }


}