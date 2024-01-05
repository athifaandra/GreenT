package com.example.banksampah;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Dropoff extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FrameLayout map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropoff);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;

        LatLng mapGreenTreasure = new LatLng(-0.9272400778695032, 100.42855566708563);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(mapGreenTreasure)
                .title("Green Treasure Location")
                .snippet("Jl. Tepi Batang Aia Gadang, Cupak Tangah, Kec. Pauh, Kota Padang, Sumatera Barat 25176");

        this.gMap.addMarker(markerOptions);
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapGreenTreasure));

    }
}