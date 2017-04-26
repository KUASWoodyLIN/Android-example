package com.example.woody_lin.lab10;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity {

    Button startMap,Typemap,TypeHybrid,TypeTerrain;
    MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMap = (Button) findViewById(R.id.button);
        Typemap = (Button) findViewById(R.id.button2);
        TypeHybrid = (Button) findViewById(R.id.button3);
        TypeTerrain = (Button) findViewById(R.id.button4);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);



        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                startMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                        intent.putExtra("Type", googleMap.getMapType());
                        startActivity(intent);
                    }
                });

                Typemap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                });

                TypeHybrid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }
                });

                TypeTerrain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    }
                });
            }
        });
    }
}
