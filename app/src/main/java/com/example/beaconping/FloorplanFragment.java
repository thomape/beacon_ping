package com.example.beaconping;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FloorplanFragment extends Fragment implements View.OnTouchListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_floorplan, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.pip);
        // Inflate the layout for this fragment
        imageView.setImageResource(R.mipmap.pip);
        return view;
    }

       @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
