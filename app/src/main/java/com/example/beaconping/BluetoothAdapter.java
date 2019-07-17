package com.example.beaconping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BluetoothAdapter extends RecyclerView.Adapter<BluetoothAdapter.ViewHolder> {

    private HashMap<Integer, Beacon> beacons;
    Beacon temp;



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView RSSI;
        public TextView distance;
        public TextView unique;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            name = (TextView) view.findViewById(R.id.beacon_name);
            RSSI = (TextView) view.findViewById(R.id.beacon_rssi);
            distance = (TextView) view.findViewById(R.id.beacon_distance);
            unique = (TextView) view.findViewById(R.id.beacon_unique);
        }
    }

    public void add(int position, Beacon item) {
        beacons.put(position, item);
    }

    public void remove(int position) {
        beacons.remove(position);
    }

    public BluetoothAdapter(HashMap<Integer, Beacon> beaconDataSet) {
        beacons = beaconDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_beacon, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Set<Integer> keys = beacons.keySet();


        for (int x = 0; x <= keys.size(); x++){

            temp = beacons.get(keys.);
            viewHolder.name.setText(String.valueOf(temp.getmMinorId()));
            viewHolder.RSSI.setText(String.valueOf(temp.getmRssi()));
            viewHolder.distance.setText(String.valueOf(temp.getmDistance()));
            viewHolder.unique.setText(temp.getmUnique());
        }
    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }


}
